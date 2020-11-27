package com.example.recpractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.recpractice.model.Favs;
import com.example.recpractice.model.Movie;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {


    private static DatabaseHelper INSTANCE;


    private static final String DATABASE_NAME = "mybase.db";
    private Dao<Movie, Integer> mMovieDao = null;
    private Dao<Favs, Integer> favsDao = null;
    public static final int DATABASE_VERSION = 1;

    public List<Movie> getMovieList() {
        List<Movie> movieList = null;
        try {
            movieList = getMovieDao().queryForAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return movieList;
    }

    private List<Favs> getFavsList() {
        List<Favs> favsList = null;
        try {
            favsList = getFavsDao().queryForAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return favsList;
    }

    public List<Movie> getFavMoviesList() {

        List<Movie> favMovieList=null;

        QueryBuilder<Movie, Integer> movieQueryBuilder = getMovieDao().queryBuilder();
        QueryBuilder<Favs, Integer> favsIntegerQueryBuilder = getFavsDao().queryBuilder();
        try {
            return movieQueryBuilder.join(favsIntegerQueryBuilder).query();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return favMovieList;
    }



    //CONSTRUCTOR
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getINSTANCE(Context context){
        if(INSTANCE==null){
            INSTANCE=new DatabaseHelper(context);
            return INSTANCE;
        }else{
            return INSTANCE;
        }

    }


    //GIVE FAVS DAO
    public Dao<Favs,Integer> getFavsDao(){
        try {
            return getDao(Favs.class);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    return null;
    }


    //GIVE MOVIE DAO
    public Dao<Movie,Integer> getMovieDao() {
        if(mMovieDao==null){
            try {
                mMovieDao=getDao(Movie.class);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return mMovieDao;
    }


    //ON CREATE
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            int res1= TableUtils.createTable(connectionSource,Movie.class);
            Log.d(TAG, "napravio tabelu movies "+res1);

            int res=TableUtils.createTable(connectionSource,Favs.class);
            Log.d(TAG, "napravio tabelu favs "+res);

            populateBase();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private void populateBase() {
        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015", R.drawable.madmax);

        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015", R.drawable.insideout);
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015", R.drawable.starwars);
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015", R.drawable.shaunthesheep);
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015", R.drawable.martian);
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015", R.drawable.missionimposible);
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009", R.drawable.up);
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009", R.drawable.startrek);
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014", R.drawable.lego);
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008", R.drawable.ironman);
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986", R.drawable.aliens);
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000", R.drawable.chicken);
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985", R.drawable.backtofuture);
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981", R.drawable.indiana);
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965", R.drawable.goldfinger);
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014", R.drawable.guardians);
        movieList.add(movie);

        for (Movie movieInput : movieList) {
            try {
                getMovieDao().create(movieInput);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    //upgrade
    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable(connectionSource,Movie.class,true);
            TableUtils.dropTable(connectionSource,Favs.class,true);
            onCreate(database,connectionSource);
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }



//CLOSE
    @Override
    public void close() {
        favsDao=null;
        mMovieDao=null;
        super.close();
    }
}
