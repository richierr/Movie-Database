package com.example.recpractice.model;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Movie.TABLE_NAME)
public class Movie {
    private static final String TABLE_NAME ="movies" ;


    private String title,genre,year;
    private int thumbnail;



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
