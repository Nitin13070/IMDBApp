package com.example.nitinvarun.imdbapp;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public static MovieListAdapter movieListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handleIntent(getIntent());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
    @Override
    protected void onNewIntent(Intent intent){
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            new HttpGetTask().execute(query);
            Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
        }
    }

    private class HttpGetTask extends AsyncTask<String,Void,List<String>>{
        String URL;
        AndroidHttpClient mClient = AndroidHttpClient.newInstance("");
        @Override
        protected List<String> doInBackground(String... params) {
            URL = "http://www.omdbapi.com/?t="+params[0]+"&y=&plot=short&r=json";
            HttpGet request = new HttpGet(URL);

            JSONResponseHandler responseHandler = new JSONResponseHandler();
            try {
                return mClient.execute(request, responseHandler);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<String> result){
            if(null != mClient){
                mClient.close();
            }
            if(result == null){
                Toast.makeText(getApplicationContext(),"Sorry No Data Found",Toast.LENGTH_SHORT).show();
                return;
            }
            movieListAdapter.add(new InformationData(result.get(0),result.get(1),result.get(2),result.get(3)));
            Toast.makeText(getApplicationContext(),result.get(0) + result.get(3),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem menuItem = menu.findItem(R.id.search);


        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(new ComponentName(getApplicationContext(),MainActivity.class)));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        ListView listView;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            listView = (ListView) rootView.findViewById(R.id.imageslist);
            movieListAdapter = new MovieListAdapter(getActivity(),R.id.container,new ArrayList<InformationData>());
            listView.setAdapter(movieListAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    InformationData informationData = movieListAdapter.getItem(position);
                    String movie =  informationData.getMovieName();
                    String director = informationData.getDirectorName();
                    String rating = informationData.getRating();
                    Toast.makeText(getActivity(),"Movie Name : "+movie+"\n"+"Director Name : "+director+"\n"+"Rating : "+rating,Toast.LENGTH_LONG).show();

                }
            });
            return rootView;
        }
    }
}
