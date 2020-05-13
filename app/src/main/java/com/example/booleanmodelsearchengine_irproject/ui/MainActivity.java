package com.example.booleanmodelsearchengine_irproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booleanmodelsearchengine_irproject.R;
import com.example.booleanmodelsearchengine_irproject.adapter.NewsAdapter;
import com.example.booleanmodelsearchengine_irproject.api.ApiClient;
import com.example.booleanmodelsearchengine_irproject.api.MyApi;
import com.example.booleanmodelsearchengine_irproject.model.News;
import com.example.booleanmodelsearchengine_irproject.response.NewsResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    @BindView(R.id.rv_news)
    RecyclerView rv_news;
    @BindView(R.id.sv_news)
    SearchView sv_news;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private MyApi myApi;
    public static final String NEWS_EXTRA = "news";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sv_news.setIconifiedByDefault(false);
        sv_news.setOnQueryTextListener(this);

        progressBar.setVisibility(View.VISIBLE);
        myApi = ApiClient.getClient().create(MyApi.class);
        getAllNews();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (query.split(" ").length > 0) {
            tv_status.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            String q = query.replace(" ","%20");
            getNewsWithQuery(q);
        }
        return true;
    }

    private void getAllNews() {
        Call<NewsResponse> newsResponseCall = myApi.getAllNews();
        Log.d("cek", "onCreate: " + newsResponseCall.request().url());
        newsResponseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    Toast.makeText(getApplicationContext() ,message, Toast.LENGTH_SHORT).show();

                    ArrayList<News> news_list = new ArrayList<>();

                    for (News news : response.body().getData()) {
                        news_list.add(news);
                    }
                    progressBar.setVisibility(View.GONE);
                    NewsAdapter newsAdapter = new NewsAdapter(news_list);
                    rv_news.setAdapter(newsAdapter);
                    rv_news.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_news.getAdapter().notifyDataSetChanged();
                    newsAdapter.setOnNewsClickCallback(news -> {
                        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                        intent.putExtra(NEWS_EXTRA,news);
                        startActivity(intent);
                    });

                    DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                    rv_news.addItemDecoration(itemDecoration);
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d("cek", t.getMessage());
            }
        });
    }

    private void getNewsWithQuery(String query) {
        Call<NewsResponse> newsResponseCall = myApi.getNewsWithQuery(query);
        newsResponseCall.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                Log.d("cek", "aaaaa: ");
                if (response.isSuccessful()) {
                    String message = response.body().getMessage();
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    ArrayList<News> news_list = new ArrayList<>();

                    for (News news : response.body().getData()) {
                        news_list.add(news);
                        Log.d("cek", "onResponse: " + news.getTitle());
                    }

                    if (news_list.size() == 0){
                        tv_status.setVisibility(View.VISIBLE);
                    }

                    progressBar.setVisibility(View.GONE);

                    NewsAdapter newsAdapter = new NewsAdapter(news_list);
                    rv_news.setAdapter(newsAdapter);
                    rv_news.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv_news.getAdapter().notifyDataSetChanged();
                    newsAdapter.setOnNewsClickCallback(news -> {
                        Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                        intent.putExtra(NEWS_EXTRA,news);
                        startActivity(intent);
                    });;
                    DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
                    rv_news.addItemDecoration(itemDecoration);


                } else {
                    progressBar.setVisibility(View.GONE);

                    Log.d("cek", response.message());
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d("cek", t.getMessage());
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}
