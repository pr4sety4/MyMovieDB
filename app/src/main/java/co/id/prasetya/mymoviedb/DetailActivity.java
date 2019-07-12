package co.id.prasetya.mymoviedb;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import co.id.prasetya.mymoviedb.util.Constanta;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle,tvTanggal,tvVoteAverage,tvOverview;
    ImageView imgBackdrop;
    String title;
    String tanggal;
    String overview;
    String backdrop_path;
    float vote_average;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvTitle = findViewById(R.id.tvTitle);
        tvTanggal = findViewById(R.id.tvTanggal);
        tvVoteAverage = findViewById(R.id.tvDetailVoteAverage);
        tvOverview = findViewById(R.id.tvOverview);
        imgBackdrop = findViewById(R.id.imageBackdrop);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        tanggal = intent.getStringExtra("tanggal");
        overview = intent.getStringExtra("overview");
        vote_average = intent.getFloatExtra("vote_average",0);
        backdrop_path = intent.getStringExtra("backdrop_path");

        setTitle(title);

        Toast.makeText(getApplicationContext(),"vote : "+vote_average,Toast.LENGTH_SHORT).show();

        tvTitle.setText(title);
        tvTanggal.setText(tanggal);
        tvVoteAverage.setText(""+vote_average);
        tvOverview.setText(overview);
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
        Glide.with(this).load(Constanta.BASE_BACKDROP_PATH_IMAGE_URL+backdrop_path).apply(requestOptions).into(imgBackdrop);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
