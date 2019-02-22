package com.madelene.pertemuan5_praktikum;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.madelene.pertemuan5_praktikum.Adapter.ArticlesAdapter;
import com.madelene.pertemuan5_praktikum.Entity.Articles;
import com.madelene.pertemuan5_praktikum.Entity.ArticlesResponse;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ArticlesAdapter.DataClickListener{

    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.rv_data)
    RecyclerView rvData;

    private ArticlesAdapter articlesAdapter;

    public ArticlesAdapter getArticlesAdapter() {
        if (articlesAdapter == null) {
            articlesAdapter = new ArticlesAdapter();
            articlesAdapter.setListener(this);
        }

        return articlesAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        DividerItemDecoration decoration = new DividerItemDecoration(this, manager.getOrientation());
        rvData.addItemDecoration(decoration);
        rvData.setLayoutManager(manager);
        rvData.setAdapter(getArticlesAdapter());

    }


    @OnClick(R.id.btnSearch)
    public void searchClicked() {
        loadArticles(String.valueOf(etSearch.getText()));


    }

    private void loadArticles(String search) {
        Uri uri = Uri.parse("https://newsapi.org/v2/everything?q=bgt").buildUpon().appendQueryParameter("apikey", "6a4fd9fb3ed24a61b3256c9d036209f3").appendQueryParameter("title",search).build();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, uri.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject object = new JSONObject();
                Gson gson = new Gson();
                ArticlesResponse articlesResponses = gson.fromJson(response, ArticlesResponse.class);

                getArticlesAdapter().setArticles(articlesResponses.getArticles());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error on response", Toast.LENGTH_SHORT).show();
            }
        }
        );
        queue.add(request);
    }

    @Override
    public void onArticleClickedListener(Articles articles) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra(getResources().getString(R.string.parcel_article), (Parcelable) articles);
        startActivity(intent);
    }
}
