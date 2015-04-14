package de.metalmatze.krautreporter.services;

import com.android.volley.Response;

import java.util.List;

import de.metalmatze.krautreporter.models.Article;

public interface ArticleService {

    List<Article> all();

    List<Article> save(List<Article> articles);

    Article find(long id);

    void update(Response.Listener listener, Response.ErrorListener errorListener);

    void fetchImages(List<Article> articles);

}