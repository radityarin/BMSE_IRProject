package com.example.booleanmodelsearchengine_irproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.booleanmodelsearchengine_irproject.R;
import com.example.booleanmodelsearchengine_irproject.model.News;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<News> list_news;
    private OnNewsClickCallback onNewsClickCallback;

    public NewsAdapter(ArrayList<News> list_news) {
        this.list_news = list_news;
    }

    public void setOnNewsClickCallback(OnNewsClickCallback onNewsClickCallback){
        this.onNewsClickCallback = onNewsClickCallback;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return list_news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_news_title)
        TextView tv_news_title;
        @BindView(R.id.tv_news_link)
        TextView tv_news_link;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void onBind(int position) {
            News news = list_news.get(position);
            tv_news_title.setText(news.getTitle());
            tv_news_link.setText(news.getLink());
            itemView.setOnClickListener(v -> onNewsClickCallback.onNewsClicked(news));
        }
    }
    public interface OnNewsClickCallback {
        void onNewsClicked(News news);
    }
}