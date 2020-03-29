package com.aidil.projectaidil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.aidil.projectaidil.adapter.MovieAdapter;
import com.aidil.projectaidil.model.Movies;
import com.aidil.projectaidil.network.ApiServices;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopularMovieActivity extends AppCompatActivity {

    private RecyclerView rvMovie;
    private MovieAdapter movieAdapter;
    private int page = 1;
    private ApiServices apiServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movie);
        rvMovie = findViewById(R.id.rv_movie);
        movieAdapter = new MovieAdapter(getBaseContext());
        rvMovie.setLayoutManager(new GridLayoutManager(this,2));
        rvMovie.setHasFixedSize(true);
        rvMovie.setAdapter(movieAdapter);
        load();
    }

    private void load() {
        apiServices = new ApiServices();
        apiServices.gePopular(page, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Movies movies = (Movies)response.body();
                if (movies != null) {
                    if (movieAdapter != null) {
                        movieAdapter.addAll(movies.getResults());
                    }
                }else {
                    Toast.makeText(PopularMovieActivity.this, "No Data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    Toast.makeText(PopularMovieActivity.this, "Request Time Out", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(PopularMovieActivity.this, "Connection Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
