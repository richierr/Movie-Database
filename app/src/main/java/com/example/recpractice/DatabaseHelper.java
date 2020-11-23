package com.example.recpractice;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.recpractice.model.Favs;
import com.example.recpractice.model.Movie;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import static android.content.ContentValues.TAG;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME="mybase.db";
    private Dao<Movie,Integer> mMovieDao=null;
    private Dao<Favs,Integer> favsDao=null;
    public static final int DATABASE_VERSION=1;


    //CONSTRUCTOR
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //GIVE FAVS DAO
    public Dao<Favs,Integer> getFavsDao()throws SQLException{
        if(favsDao==null){
            favsDao=getDao(Favs.class);
        }
        return favsDao;
    }


    //GIVE MOVIE DAO
    public Dao<Movie,Integer> getMovieDao() throws SQLException {
        if(mMovieDao==null){
            mMovieDao=getDao(Movie.class);
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
        }catch (SQLException e){
            throw new RuntimeException(e);
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
