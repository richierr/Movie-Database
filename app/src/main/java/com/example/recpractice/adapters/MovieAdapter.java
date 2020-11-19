package com.example.recpractice.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recpractice.R;
import com.example.recpractice.model.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {
    private List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myHolder=(MyViewHolder)holder;
        Movie movie=movieList.get(position);
        myHolder.title.setText(movie.getTitle());
        myHolder.year.setText(movie.getYear());
        myHolder.genre.setText(movie.getGenre());




    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title,year,genre;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title=(TextView) itemView.findViewById(R.id.title);
            year=(TextView) itemView.findViewById(R.id.year);
            genre=(TextView) itemView.findViewById(R.id.genre);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), ""+getAdapterPosition()+movieList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }


    }
}
