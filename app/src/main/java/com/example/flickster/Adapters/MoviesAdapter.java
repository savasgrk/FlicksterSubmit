package com.example.flickster.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flickster.models.Movie;
import com.example.flickster.R;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
{
    Context context;
    List <Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) //used generate constructor
    {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("smile", "onCreateViewHolder");
        View view= LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d("smile", "onCreateViewHolder" +position);
        Movie movie = movies.get(position);
        //Bind movie data into the view holder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPosters;

        public ViewHolder(View itemview) {
            super(itemview);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvOverview=itemView.findViewById(R.id.tvOverview);
            ivPosters=itemView.findViewById(R.id.ivPoster);

        }
        public void bind(Movie movie)
        {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText((movie.getOverview()));
            Glide.with(context).load(movie.getPosterPath()).into(ivPosters);
        }
    }
}
