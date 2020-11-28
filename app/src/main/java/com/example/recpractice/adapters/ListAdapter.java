package com.example.recpractice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recpractice.DatabaseHelper;
import com.example.recpractice.R;

import com.example.recpractice.fragments.ListFragment;
import com.example.recpractice.model.Movie;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter {
    private List<Movie> movieList;
    private ListFragment.OnMovieItemClickInteface mListener;
    private int mIndex;
    private Context mContext;
    private boolean isMainList;

    public ListAdapter(List<Movie> movieList, ListFragment.OnMovieItemClickInteface mListener,boolean isMainList) {
        this.movieList = movieList;
        this.mListener = mListener;
        this.isMainList=isMainList;



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
        Button addToFavsBtn;
        int movieItemId;

        public MyListViewHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.itemTitle);
            genre=itemView.findViewById(R.id.itemGenre);
            year=itemView.findViewById(R.id.itemYear);
            image=itemView.findViewById(R.id.itemImage);
            addToFavsBtn=itemView.findViewById(R.id.addToFavsBtn);


            addToFavsBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favsBtnAction(movieItemId);

                }
            });
            itemView.setOnClickListener(this);
        }

        private void favsBtnAction(int clickedOnId) {
                    if(isMainList){
                        DatabaseHelper.getINSTANCE(mContext).addToFavsList(clickedOnId);
                    }else{
                        DatabaseHelper.getINSTANCE(mContext).removeFromFavsList(clickedOnId);
                        removeMovieFromList(clickedOnId);
                        ListAdapter.this.notifyDataSetChanged();


                    }
        }

        private void removeMovieFromList(int index){
            for(Movie movie:movieList){
                if(movie.getId()==index){
                    movieList.remove(movie);
                    return;
                }
            }
        }

        public void bindView(int position){
            movieItemId=movieList.get(position).getId();

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
