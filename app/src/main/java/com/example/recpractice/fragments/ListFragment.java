package com.example.recpractice.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recpractice.DatabaseHelper;
import com.example.recpractice.R;
import com.example.recpractice.adapters.ListAdapter;
import com.example.recpractice.model.Movie;

import java.util.List;

public abstract class ListFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        OnMovieItemClickInteface mListener=(OnMovieItemClickInteface)getActivity();
        View view=inflater.inflate(R.layout.fragment_list,container,false);

        RecyclerView recyclerView=view.findViewById(R.id.listRecyclerView);
        ListAdapter listAdapter=new ListAdapter(getListData(), mListener);
        recyclerView.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.addItemDecoration(new DividerItemDecoration(container.getContext(), LinearLayoutManager.VERTICAL));

        recyclerView.setLayoutManager(layoutManager);
        return view;
    }


    public interface OnMovieItemClickInteface{

        void onMovieItemClick(int index);
    }


    abstract List<Movie> getListData();

}
