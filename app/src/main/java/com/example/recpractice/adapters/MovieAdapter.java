package com.example.recpractice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recpractice.DatabaseHelper;
import com.example.recpractice.R;
import com.example.recpractice.model.Favs;
import com.example.recpractice.model.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;


import java.sql.SQLException;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<Movie> movieList;
     Dao<Favs,Integer> favsIntegerDao;
     DatabaseHelper databaseHelper;
     private MovieAdapter movieAdapter = this;

    public MovieAdapter(List<Movie> movieList, Context context)  {
        this.databaseHelper=DatabaseHelper.getINSTANCE(context);
        this.mContext=context;
        this.movieList = movieList;
        favsIntegerDao = DatabaseHelper.getINSTANCE(context).getFavsDao();

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myHolder=(MyViewHolder)holder;
        Movie movie=movieList.get(position);
        myHolder.title.setText(movie.getTitle());
        myHolder.year.setText(movie.getYear());
        myHolder.genre.setText(movie.getGenre());

        Glide.with(mContext).load(movie.getThumbnail()).into(myHolder.imageView);
        myHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(myHolder.imageView,movie.getId());
            }
       });
    }





    private void showPopUpMenu(View view,int id ){
        PopupMenu popupMenu=new PopupMenu(mContext, view);
        MenuInflater inflater=popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_movie,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener(id));
        popupMenu.show();
    }
class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
    int movieId;
        public MyMenuItemClickListener(int movieId) {
            this.movieId=movieId;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add_favourite:
                Toast.makeText(mContext, "Added to fav "+movieId, Toast.LENGTH_SHORT).show();

                try {
                    Movie movie =DatabaseHelper.getINSTANCE(mContext).getMovieDao().queryForId(movieId);
                    Favs favs=new Favs(movieId,3.14d,databaseHelper.getMovieDao().queryForId(movieId));

                    favsIntegerDao.create(favs);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                return true;
            case R.id.remove:
                try {
                    Toast.makeText(mContext, "Removed "+favsIntegerDao.queryForAll().get(0).getRating(), Toast.LENGTH_SHORT).show();

                    DeleteBuilder<Favs,Integer> deleteBuilder=favsIntegerDao.deleteBuilder();
                    deleteBuilder.where().eq("movie_id",movieId);
                    deleteBuilder.delete();
                    deleteItemFromTheList(movieId);

                    movieAdapter.notifyDataSetChanged();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                return true;
            default:
        }
        return false;
    }
}

    @Override
    public int getItemCount() {
        return movieList.size();
    }


private void deleteItemFromTheList(int position){
        for(Movie movie:movieList){
            if(movie.getId()==position){
                movieList.remove(movie);
return;
            }
        }
}


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title,year,genre;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.mImageView);
            title= itemView.findViewById(R.id.txtVTittle);
            year= itemView.findViewById(R.id.txtGenre);
            genre= itemView.findViewById(R.id.txtYear);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), ""+getAdapterPosition()+movieList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }


    }
}
