package com.madelene.pertemuan5_praktikum.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madelene.pertemuan5_praktikum.Entity.Articles;
import com.madelene.pertemuan5_praktikum.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>  {

    private ArrayList<Articles> articles;
    public DataClickListener listener;

    public void setListener(DataClickListener listener) {
        this.listener = listener;
    }

    public ArrayList<Articles> getArticles() {
        if(articles==null){
            articles = new ArrayList<>();

        }
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.getArticles().clear();
        this.getArticles().addAll(articles) ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row,viewGroup,false);

        return new ArticlesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesViewHolder articlesViewHolder, int i) {
    final Articles articles = getArticles().get(i);
    articlesViewHolder.txtTitle.setText(articles.getTitle());
    articlesViewHolder.txtAuthor.setText(articles.getAuthor());
    articlesViewHolder.txtDesc.setText(articles.getDescription());
    articlesViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listener.onArticleClickedListener(articles);
        }
    });

    }

    @Override
    public int getItemCount() {
        return getArticles().size();
    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtDesc)
        TextView txtDesc;

        @BindView(R.id.txtAuthor)
        TextView txtAuthor;
        public ArticlesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface DataClickListener{
        void onArticleClickedListener(Articles articles);

    }
}
