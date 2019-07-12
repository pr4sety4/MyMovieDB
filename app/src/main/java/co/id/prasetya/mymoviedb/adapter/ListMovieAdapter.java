package co.id.prasetya.mymoviedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import co.id.prasetya.mymoviedb.R;
import co.id.prasetya.mymoviedb.model.ListMovieResults;
import co.id.prasetya.mymoviedb.util.Constanta;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<ListMovieResults> mListMovie;
    OnItemClickListener listener;

    public ListMovieAdapter(Context mContext, ArrayList<ListMovieResults> mListMovie, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mListMovie = mListMovie;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListMovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new ListMovieAdapter.MyViewHolder(v);
    }

    public interface OnItemClickListener {
            void onItemClick(ListMovieResults listMovieResults);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieAdapter.MyViewHolder holder, int position) {
        final ListMovieResults listMovieResults = mListMovie.get(position);
        holder.tvTitle.setText(listMovieResults.getTitle());
        holder.tvDate.setText(listMovieResults.getRelease_date());
        holder.tvOverview.setText(listMovieResults.getOverview());
        holder.tvVoteAverage.setText("" + listMovieResults.getVote_average());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(listMovieResults);
            }
        });
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background);
        Glide.with(mContext).load(Constanta.BASE_IMAGE_URL+listMovieResults.getPoster_path()).apply(requestOptions).into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return mListMovie.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPoster;
        TextView tvTitle,tvDate,tvVoteAverage,tvOverview;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPoster=itemView.findViewById(R.id.imgViewPoster);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvDate=itemView.findViewById(R.id.tvTanggal);
            tvVoteAverage=itemView.findViewById(R.id.tvVoteAverage);
            tvOverview=itemView.findViewById(R.id.tvOverview);

            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
