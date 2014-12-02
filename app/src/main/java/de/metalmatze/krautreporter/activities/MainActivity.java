package de.metalmatze.krautreporter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import de.metalmatze.krautreporter.R;
import de.metalmatze.krautreporter.adapters.ArticleRecyclerViewAdapter;
import de.metalmatze.krautreporter.models.ArticleModel;
import de.metalmatze.krautreporter.services.ArticleService;

public class MainActivity extends ActionBarActivity implements ArticleRecyclerViewAdapter.OnItemClickListener {

    protected RecyclerView recyclerView;
    protected ArticleRecyclerViewAdapter recyclerViewAdapter;
    protected ArticleService articleService;

    private List<ArticleModel> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.articleService = new ArticleService(getApplicationContext());

        this.setContentView(R.layout.activity_main);

        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        this.recyclerView.setLayoutManager(layoutManager);

        this.articleService.update();
        this.articles = this.articleService.all();

        this.recyclerViewAdapter = new ArticleRecyclerViewAdapter(getApplicationContext(), this, this.articles);
        this.recyclerView.setAdapter(this.recyclerViewAdapter);
    }

    @Override
    public void onItemClick(ArticleModel articleModel) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra("id", articleModel.getId());

        this.startActivity(intent);
    }
}
