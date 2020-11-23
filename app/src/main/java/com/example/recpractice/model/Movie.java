package com.example.recpractice.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;

@DatabaseTable(tableName = Movie.TABLE_NAME)
public class Movie {
    public static final String TABLE_NAME ="movies" ;

public static final String TITLE="title",GENRE="genre",YEAR="year",THUMBNAIL="thumbnail",ID="id";

       // Dao<Movie, Integer> mMovieDao = null;

    @DatabaseField(columnName =TITLE)
    private String title;

    @DatabaseField(columnName = GENRE)
    private String genre;
    @DatabaseField(columnName =YEAR)
    private String year;
    @DatabaseField(columnName =THUMBNAIL)
    private int thumbnail;
    @DatabaseField(columnName =ID,generatedId = true)
    private int id;

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Movie(String title, String genre, String year,int thumbnail) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.thumbnail=thumbnail;
    }



}
