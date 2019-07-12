package co.id.prasetya.mymoviedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

import co.id.prasetya.mymoviedb.adapter.ListMovieAdapter;
import co.id.prasetya.mymoviedb.model.ListMovieResults;
import co.id.prasetya.mymoviedb.model.MovieResult;
import co.id.prasetya.mymoviedb.util.Constanta;
import co.id.prasetya.mymoviedb.util.retrofit.ApiClient;
import co.id.prasetya.mymoviedb.util.retrofit.ApiServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ListMovieAdapter listMovieAdapter;
    ArrayList<ListMovieResults> listMovieResults = new ArrayList<>();

    int page=1;
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        listMovieAdapter = new ListMovieAdapter(this, listMovieResults, new ListMovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListMovieResults listMovieResults) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("title",listMovieResults.getTitle());
                intent.putExtra("tanggal",listMovieResults.getRelease_date());
                intent.putExtra("overview",listMovieResults.getOverview());
                intent.putExtra("vote_average",listMovieResults.getVote_average());
                intent.putExtra("backdrop_path",listMovieResults.getBackdrop_path());
                startActivity(intent);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listMovieAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!recyclerView.canScrollVertically(1)){
                    ++page;
                    if(isLoading==false){
                        getDataWithRetrofit();
                    }
                    isLoading=true;
                }

            }
        });

        getDataWithRetrofit();
    }

    private void getDataWithRetrofit() {
        MovieResult movieResult= new MovieResult();

        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Content loading....");
        progressDoalog.setTitle("Loading...");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        // show it
        progressDoalog.show();

        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);
        Call<MovieResult> call = apiServices.getListMovies(Constanta.V3_AUTH,page,"popularity.desc");

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.body() != null) {
                    progressDoalog.dismiss();
                    listMovieResults.addAll(response.body().getListMovieResults());
                    listMovieAdapter.notifyDataSetChanged();
                }
                isLoading=false;
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(getApplicationContext(), "Error" + t, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
                isLoading=false;
            }
        });

    }

}
