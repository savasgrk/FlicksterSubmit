package com.example.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie
{
    String posterPath, title, overview, backdropPath;

    public Movie(JSONObject jsonObject) throws JSONException //who ever calls the method has to deal with the catch
    {
        posterPath = jsonObject.getString("poster_path");
        title=jsonObject.getString("title");
        overview=jsonObject.getString("overview");
        backdropPath=jsonObject.getString("backdrop_path");


    }
    public static List<Movie> fromJSONArray(JSONArray movieJsonArray) throws JSONException
    {
        List<Movie> movies = new ArrayList<>();
        for(int i =0; i< movieJsonArray.length(); i++) //for loop to add the movies
        {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() { //used the generator for these three getters
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
