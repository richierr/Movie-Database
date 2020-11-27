package com.example.recpractice.fragments;

import com.example.recpractice.DatabaseHelper;
import com.example.recpractice.model.Movie;

import java.util.List;

public class FavsListFragment extends ListFragment {
    @Override
    List<Movie> getListData() {
        return DatabaseHelper.getINSTANCE(getContext()).getFavMoviesList();
    }
}
