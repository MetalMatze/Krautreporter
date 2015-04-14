package de.metalmatze.krautreporter.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import de.metalmatze.krautreporter.R;
import de.metalmatze.krautreporter.fragments.ArticleDetailFragment;
import de.metalmatze.krautreporter.helpers.Mixpanel;

public class ArticleDetailActivity extends ActionBarActivity implements ArticleDetailFragment.ActionBarTitle {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article_detail);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putInt(ArticleDetailFragment.ARTICLE_ID,
                    getIntent().getIntExtra(ArticleDetailFragment.ARTICLE_ID, -1));

            ArticleDetailFragment fragment = new ArticleDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.article_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        Mixpanel.getInstance(this).flush();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_list, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, ArticleListActivity.class));
            return true;
        }

        if (id == R.id.action_become_member) {
            Mixpanel.getInstance(this).track(getString(R.string.mixpanel_become_member), null);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://krautreporter.de/pages/mitglied_werden"));

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setActionBarTitle(String title) {
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}