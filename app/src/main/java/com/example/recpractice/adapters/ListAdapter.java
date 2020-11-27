package com.example.recpractice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recpractice.R;

import com.example.recpractice.fragments.ListFragment;
import com.example.recpractice.model.Movie;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {
    private List<Movie> movieList;
    private ListFragment.OnMovieItemClickInteface mListener;
    private int mIndex;
    private Context mContext;

    public ListAdapter(List<Movie> movieList, ListFragment.OnMovieItemClickInteface mListener) {
        this.movieList = movieList;
        this.mListener = mListener;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        mContext=parent.getContext();
        return new MyListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      MyListViewHolder myListViewHolder=(MyListViewHolder)holder;
      myListViewHolder.bindView(position);

    }




    @Override
    public int getItemCount() {
        return movieList.size();
    }


    class MyListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView genre;
        TextView year;
        ImageView image;

        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.itemTitle);
            genre=itemView.findViewById(R.id.itemGenre);
            year=itemView.findViewById(R.id.itemYear);
            image=itemView.findViewById(R.id.itemImage);
            itemView.setOnClickListener(this);
        }
        public void bindView(int position){
            mIndex=position;
            title.setText(movieList.get(position).getTitle());
            genre.setText(movieList.get(position).getGenre());
            year.setText(movieList.get(position).getYear());
            Glide.with(mContext).load(movieList.get(position).getThumbnail()).into(image);
            this.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //// TODO: dodaj onclick listener za popupmenu
                }
            });
        }

        @Override
        public void onClick(View v) {
            mListener.onMovieItemClick(mIndex);

        }
    }
}
