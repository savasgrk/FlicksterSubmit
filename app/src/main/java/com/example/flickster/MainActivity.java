package com.example.flickster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.flickster.Adapters.MoviesAdapter;
import com.example.flickster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import cz.msebera.android.httpclient.Header;
import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private static final String MOVIE_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    List<Movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvMovies= findViewById(R.id.rvMovies);
        movies = new ArrayList<>();
        final MoviesAdapter adapter=new MoviesAdapter(this, movies); //creates an instance of movie adapter
        rvMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvMovies.setAdapter(adapter);


        AsyncHttpClient client = new AsyncHttpClient();//movie data base api
        client.get(MOVIE_URL, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response)
            {
                try
                {
                    JSONArray movieJsonArray= response.getJSONArray("results"); //gets key
                    movies.addAll(Movie.fromJSONArray(movieJsonArray)); //puts the movies in a seperate class
                    adapter.notifyDataSetChanged(); //lets the adapter know the data was changed

                    Log.d("smile", movies.toString());
                }
                catch (JSONException e)
                {
                    e.printStackTrace(); //if we get an exception program still runs
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}