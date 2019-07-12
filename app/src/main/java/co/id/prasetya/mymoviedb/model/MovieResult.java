package co.id.prasetya.mymoviedb.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResult {
    int page;
    int total_pages;
    @SerializedName("results")
    ArrayList<ListMovieResults> listMovieResults;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<ListMovieResults> getListMovieResults() {
        return listMovieResults;
    }

    public void setListMovieResults(ArrayList<ListMovieResults> listMovieResults) {
        this.listMovieResults = listMovieResults;
    }
}
