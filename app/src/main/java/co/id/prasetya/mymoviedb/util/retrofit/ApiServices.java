package co.id.prasetya.mymoviedb.util.retrofit;

import java.util.ArrayList;

import co.id.prasetya.mymoviedb.model.ListMovieResults;
import co.id.prasetya.mymoviedb.model.MovieResult;
import co.id.prasetya.mymoviedb.util.Constanta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {

    @GET("movie")
    Call<MovieResult> getListMovies(@Query("api_key") String api_key, @Query("page") int page, @Query("sort_by")String sort);

}
