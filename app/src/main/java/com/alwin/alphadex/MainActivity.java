package com.alwin.alphadex;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
//    public static String websiteAdres = "http://192.168.1.58/school/alphaDex/api.php";
    public static String websiteAdres = "http://10.125.100.129/school/alphaDex/api.php";

//    private GridView imageGrid;
//    private ArrayList<Bitmap> bitmapList;
//    private ArrayList<String> namesList;
    private SwipeRefreshLayout swipeLayout;
    public ArrayList namesList = new ArrayList();
    public ArrayList bitmapList = new ArrayList();
    public ArrayList imageUrls = new ArrayList();
    public ArrayList ids = new ArrayList();
    private final static String DETAILS_FRAGMENT_TAG = "DetailsFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                GridView imageGrid = (GridView) findViewById(R.id.grid);

                new makeGrid().execute(imageGrid, false, "", true, swipeLayout);
            }
        });
        swipeLayout.setColorSchemeResources(R.color.colorPokemonGreen, R.color.colorPokemonYellow, R.color.colorPokemonRed, R.color.colorPokemonOrange);
                swipeLayout.setProgressBackgroundColorSchemeResource(R.color.colorPokemonBlue);


        final EditText editText = (EditText) findViewById(R.id.search_text);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {

                    showLoader(true);
                    GridView imageGrid = (GridView) findViewById(R.id.grid);
                    new makeGrid().execute(imageGrid, true, editText.getText().toString(), false);
                    handled = true;
                }
                return handled;
            }
        });
        ImageButton startSearch = (ImageButton) findViewById(R.id.start_search);
        startSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showLoader(true);
                GridView imageGrid = (GridView) findViewById(R.id.grid);
                new makeGrid().execute(imageGrid, true, editText.getText().toString(), false);
            }
        });
        ImageButton stopSearch = (ImageButton) findViewById(R.id.stop_search);
        stopSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout searchBox = (LinearLayout) findViewById(R.id.search_box);
                if (searchBox.getVisibility() == View.VISIBLE) {
                    searchBox.setVisibility(View.GONE);
                }
            }
        });


        showLoader(true);
        GridView imageGrid = (GridView) findViewById(R.id.grid);

        new makeGrid().execute(imageGrid,false,"",false);



        imageGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d("img", "clicked: " + String.valueOf(i));

//                selectedItem = i;
                startDetailsFragment(i);

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // frate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_search) {
//
//
//        }



        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            LinearLayout searchBox = (LinearLayout) findViewById(R.id.search_box);


//            EditText search = (EditText) findViewById(R.id.search_text);
            if(searchBox.getVisibility() == View.VISIBLE){
                searchBox.setVisibility(View.GONE);
            }else{
                searchBox.setVisibility(View.VISIBLE);
            }
            closeDetailsFragment();
        }else if (id == R.id.nav_all) {
            LinearLayout searchBox = (LinearLayout) findViewById(R.id.search_box);
//            EditText search = (EditText) findViewById(R.id.search_text);
            searchBox.setVisibility(View.GONE);
            showLoader(true);
            GridView imageGrid = (GridView) findViewById(R.id.grid);
            new makeGrid().execute(imageGrid, false, "", false);
            closeDetailsFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }


    private class makeGrid extends AsyncTask<Object, Void, Boolean> {

        GridView grid;
        SwipeRefreshLayout swipe;
        Boolean swipeLayout;
        Boolean search;
        String searchString = "";

        @Override
        protected Boolean doInBackground(Object... urls) {


            grid = (GridView) urls[0];

            swipeLayout = (Boolean) urls[3];

            if(swipeLayout){
                swipe = (SwipeRefreshLayout) urls[4];
            }
            search = (Boolean) urls[1];

//            bitmapList = new ArrayList<Bitmap>();

            Boolean good = true;
            try {

                InputStream inputStream;
                String result = "";
                try {
                    URL url = new URL(MainActivity.websiteAdres + "?items");
                    if(search){
                        searchString = (String) urls[2];
                        url = new URL(MainActivity.websiteAdres + "?search="+searchString);
                    }
                    URLConnection urlConnection = url.openConnection();
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    result = inputStream != null ? convertInputStreamToString(inputStream) : "false";

                } catch (Exception e) {
                    Log.d("InputStream", "" + e.getLocalizedMessage());
                    Log.d("InputStream", ""+e.toString());
                    result = "false";
                    good = false;
                }
                if(search){
                    bitmapList = new ArrayList();
                    imageUrls = new ArrayList();
                    namesList = new ArrayList();
                    ids = new ArrayList();
                }
                if(result != "false" && result != "") {
                    JSONArray items = new JSONArray(result);
                    for(int i=0;i<items.length();i++){
                        JSONObject item = new JSONObject(items.get(i).toString());
                        if(!imageUrls.contains(item.getString("image"))){
                            bitmapList.add(urlImageToBitmap(item.getString("image")));
                            imageUrls.add(item.getString("image"));
                            namesList.add( item.getString("name") );
                            ids.add( item.getString("id") );
                        }
                    }
                }
            } catch (Exception e) {
                Log.d("error", e.toString());
                good = false;
            }
            return good;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean done) {
            if(done){
                if(swipeLayout){
                    swipe.setRefreshing(false);
                }
                grid.removeAllViewsInLayout();
                grid.setAdapter(new ImageAdapter(getBaseContext(), bitmapList, namesList));
                ProgressBar loader = (ProgressBar) findViewById(R.id.loader);
                loader.setVisibility(View.GONE);
            }else{
                new makeGrid().execute(grid, search, searchString, swipeLayout, swipe);
            }
        }
    }


    public void showLoader(Boolean show) {

        ProgressBar loader = (ProgressBar) findViewById(R.id.loader);

        loader.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public static Bitmap urlImageToBitmap(String imageUrl) throws Exception {
        Bitmap result = null;
        URL url = new URL(imageUrl);
        if(url != null) {
            result = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        return result;
    }
    public void startDetailsFragment(int i) {

        getIntent().putExtra("item", ids.get(i).toString());
        DetailsFragment details = new DetailsFragment();
        details.setArguments(getIntent().getExtras());
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.details, details);
        ft.addToBackStack(DETAILS_FRAGMENT_TAG);
        ft.commit();

        LinearLayout gridList = (LinearLayout) findViewById(R.id.grid_list);
        gridList.setVisibility(View.GONE);
        FrameLayout relativeDetail = (FrameLayout) findViewById(R.id.details);
        relativeDetail.setVisibility(View.VISIBLE);

    }
    public void closeDetailsFragment(){

        LinearLayout gridList = (LinearLayout) findViewById(R.id.grid_list);
        gridList.setVisibility(View.VISIBLE);
        FrameLayout relativeDetail = (FrameLayout) findViewById(R.id.details);
        relativeDetail.setVisibility(View.GONE);
    }

}
