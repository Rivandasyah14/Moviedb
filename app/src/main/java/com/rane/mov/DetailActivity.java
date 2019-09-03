package com.rane.mov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rane.mov.model.Model;

public class DetailActivity extends AppCompatActivity {
    public final static String Extra = "extra";

    Model mModel;
    ImageView imgDetail;
    TextView tvName, tvDate, tvOverview, tvRatingBar;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgDetail = findViewById(R.id.img_detail);
        tvName = findViewById(R.id.tv_title);
        tvDate = findViewById(R.id.rating_label);
        tvOverview = findViewById(R.id.tv_title_overview);
        ratingBar = findViewById(R.id.rating_bar);

        mModel = getIntent().getParcelableExtra(Extra);
        if (mModel != null) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(mModel.getName());
            }
            Glide.with(this).load(mModel.getPoster_path())
                    .apply(new RequestOptions().override(350,550))
                    .placeholder(R.drawable.default_picture).error(R.drawable.default_picture).into(imgDetail);

            tvName.setText(mModel.getName());
            tvDate.setText(mModel.getFirst_air_date());
            tvOverview.setText(mModel.getOverview());

            float rat = mModel.getVote_average().floatValue()/2;
            String labelRating = Float.toString(rat);

            tvRatingBar.setText(getResources().getString(R.string.label_rating, labelRating));
            ratingBar.setRating(rat);
        }
    }
}
