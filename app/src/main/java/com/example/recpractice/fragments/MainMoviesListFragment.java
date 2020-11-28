package com.example.recpractice.fragments;


import com.example.recpractice.DatabaseHelper;
import com.example.recpractice.model.Movie;

import java.util.List;

public class MainMoviesListFragment extends ListFragment {
    @Override
    List<Movie> getListData() {
        getActivity().setTitle("All movies");
        return DatabaseHelper.getINSTANCE(getContext()).getMovieList();
    }
}
