package com.example.nitinvarun.imdbapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by NitinVarun on 6/4/2015.
 */
public class MovieListAdapter extends ArrayAdapter<InformationData> {

    Context context;
    ArrayList<InformationData> objects;
    ViewHolder holder;

    public MovieListAdapter(Context context, int resource, ArrayList<InformationData> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }
    public InformationData getItem(int pos){
        return objects.get(pos);
    }
    @Override
    public View getView(int position, View convertView,ViewGroup parent){
        View view = convertView;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.movie_list_item, parent, false);

        }
        holder = (ViewHolder) view.getTag();

        if (holder == null) {
            holder = new ViewHolder();
            holder.image = (ImageView) view.findViewById(R.id.list_image);
            holder.name = (TextView) view.findViewById(R.id.title);
            holder.directorName = (TextView) view.findViewById(R.id.director_name);
            holder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            view.setTag(holder);
        }


        Picasso.with(context).load(objects.get(position).getPosterURL()).resize(100,100).into(holder.image);
        holder.ratingBar.setRating(Float.parseFloat(objects.get(position).getRating()));

        holder.directorName.setText(objects.get(position).getDirectorName());
        holder.name.setText(objects.get(position).getMovieName());
        return view;
    }

    public static class ViewHolder{
        ImageView image;
        TextView name;
        TextView directorName;
        RatingBar ratingBar;

    }
}
