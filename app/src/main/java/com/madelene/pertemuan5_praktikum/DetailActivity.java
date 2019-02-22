package com.madelene.pertemuan5_praktikum;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.madelene.pertemuan5_praktikum.Entity.Articles;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.txtAuthorr)
    TextView txtAuthor;

    @BindView(R.id.txtContentt)
    TextView txtContent;

    @BindView(R.id.txtDescc)
    TextView txtDescc;

    @BindView(R.id.txtTitlee)
    TextView txtTitle;

    @BindView(R.id.txtPubDate)
    TextView txtPubDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        if (getIntent().hasExtra(getResources().getString(R.string.parcel_article))) {
            Articles articles = getIntent().getParcelableExtra(getResources().getString(R.string.parcel_article));

            txtAuthor.setText(articles.getAuthor());
            txtDescc.setText(articles.getDescription());
            txtContent.setText(articles.getContent());
            txtTitle.setText(articles.getTitle());
            txtPubDate.setText(articles.getPublishedAt());

        }
    }
}
