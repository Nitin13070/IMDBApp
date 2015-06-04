package com.example.nitinvarun.imdbapp;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by NitinVarun on 6/4/2015.
 */
public class ListFragment extends Fragment {
    Context mContext;
    ListView imageListView;
    public ListFragment() {

    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.mContext = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        imageListView = (ListView) rootView.findViewById(R.id.imageslist);


        return rootView;
    }

}
