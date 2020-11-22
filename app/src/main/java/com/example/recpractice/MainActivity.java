package com.example.recpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.recpractice.adapters.MovieAdapter;
import com.example.recpractice.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> movieList;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieList=new ArrayList<>();
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rec_view);
        mAdapter=new MovieAdapter(movieList,getApplicationContext());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Log.d(TAG, "onCreate: ");





        recyclerView.setAdapter(mAdapter);


        prepareMovieData();


    }


    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015",R.drawable.madmax);
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015",R.drawable.insideout);
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015",R.drawable.starwars);
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015",R.drawable.shaunthesheep);
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015",R.drawable.martian);
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015",R.drawable.missionimposible);
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009",R.drawable.up);
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009",R.drawable.startrek);
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014",R.drawable.lego);
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008",R.drawable.ironman);
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986",R.drawable.aliens);
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000",R.drawable.chicken);
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985",R.drawable.backtofuture);
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981",R.drawable.indiana);
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965",R.drawable.goldfinger);
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014",R.drawable.guardians);
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}