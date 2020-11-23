package com.example.recpractice.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Favs.TABLE_NAME)
public class Favs {

    static final String TABLE_NAME ="favs";
    private static final String ID="id";
    private static final String RATING="rating";


    @DatabaseField(columnName =ID)
    private int id;

    @DatabaseField(columnName = RATING)
    private double rating;


    public Favs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Favs(int id, double rating) {
        this.id = id;
        this.rating = rating;
    }
}
