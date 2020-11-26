package com.example.recpractice;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recpractice.adapters.MovieAdapter;
import com.example.recpractice.model.Favs;
import com.example.recpractice.model.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private Toolbar myToolbar;
    private RecyclerView recyclerView;
    private MovieAdapter mAdapter;
    private Dao<Favs,Integer> favsIntegerDao;
    private Dao<Movie,Integer> movieIntegerDao;
    private DatabaseHelper databaseHelper;
    private List<Favs> listOfFavs;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myToolbar = findViewById(R.id.toolbar_reusable);
        setSupportActionBar(myToolbar);



        databaseHelper= DatabaseHelper.getINSTANCE(this);
        favsIntegerDao=databaseHelper.getFavsDao();
        movieIntegerDao=databaseHelper.getMovieDao();

        prepare_recycler();

    }


    private void prepare_recycler(){
        recyclerView=findViewById(R.id.second_act_recy);
        mAdapter=new MovieAdapter(getMovieData(),this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_second_ac,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.back_icon_second_act){
            onBackPressed();
        }
        return true;
    }



    private List<Movie> getMovieData(){
        Log.i("OVAAJ1", "getMovieData: ");
        List<Movie> movies= null;
        try {
            //movies = databaseHelper.getMovieDao().queryBuilder().where().eq("id",favsIntegerDao.queryBuilder()).query();
            QueryBuilder<Movie,Integer> movieQueryBuilder=movieIntegerDao.queryBuilder();
            QueryBuilder<Favs,Integer> favsIntegerQueryBuilder=favsIntegerDao.queryBuilder();
            movies=movieQueryBuilder.join(favsIntegerQueryBuilder).query();


            Log.i("OVAAJ2", "getMovieData: ");
        } catch (SQLException throwables) {
            Log.e("IZLETEO OVDE", "getMovieData: ");
            throwables.printStackTrace();
        }
return movies;
    }


}
