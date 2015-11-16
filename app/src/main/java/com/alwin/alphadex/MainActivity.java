package com.alwin.alphadex;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static String websiteAdres = "http://10.125.100.243/school/alphaDex/api.php";
//    public static String websiteAdres = "http://192.168.42.111/school/alphaDex/api.php";

    private GridView imageGrid;
    private ArrayList<Bitmap> bitmapList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        AsyncTask getItems = new getItems().execute("?items");



//        GridView gridView = (GridView) findViewById(R.id.grid);
//
//        gridView.setAdapter(new ImageAdapter(this));
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//                Toast.makeText(
//                        getApplicationContext(),"test", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        //TableRow item1 = (TableRow) findViewById(R.id.item1);
        //item1.setBackground(img1);

//        ImageView image1 = (ImageView) findViewById(R.id.img1);
//        image1.setImageDrawable(img1);
//        ImageView image2 = (ImageView) findViewById(R.id.img2);
//        image2.setImageDrawable(img2);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);


        this.imageGrid = (GridView) findViewById(R.id.grid);
        this.bitmapList = new ArrayList<Bitmap>();

        try {

            InputStream inputStream;
            String result = "";
            try {
                URL url = new URL(MainActivity.websiteAdres + "?items");
                URLConnection urlConnection = url.openConnection();
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                result = inputStream != null ? convertInputStreamToString(inputStream) : "false";

            } catch (Exception e) {
                Log.d("InputStream", "" + e.getLocalizedMessage());
                Log.d("InputStream", ""+e.toString());
                result = "false";
            }

            if(result != "false" && result != "") {
                try {
                    JSONArray items = new JSONArray(result);

                    for(int i=0;i<items.length();i++){
                        try{
                            JSONObject item = new JSONObject(items.get(i).toString());

    //                        Drawable img = LoadImageFromWebOperations(item.getString("image"));
    //                        thumbs[thumbs.length] = img;
                            this.bitmapList.add(urlImageToBitmap(item.getString("image")));

                        }catch (Exception e) {
                            Log.d("error", e.toString());
                        }
                    }

                } catch (Exception e) {
                    Log.d("error", e.toString());
                }
            }
//            for(int i = 0; i < 10; i++) {
//                this.bitmapList.add(urlImageToBitmap("http://placehold.it/150x150"));
//            }
        } catch (Exception e) {
//            e.printStackTrace();
            Log.d("error", e.toString());
        }

        this.imageGrid.setAdapter(new ImageAdapter(this, this.bitmapList));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

//    public static Drawable LoadImageFromWebOperations(String url) {
//        try {
//            InputStream is = (InputStream) new URL(url).getContent();
//            Drawable d = Drawable.createFromStream(is, "img");
//            return d;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private class getItems extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... urls) {
//
//            InputStream inputStream;
//            String result = "";
//            try {
//                URL url = new URL(websiteAdres + urls[0]);
//                URLConnection urlConnection = url.openConnection();
//                inputStream = new BufferedInputStream(urlConnection.getInputStream());
//
//                result = inputStream != null ? convertInputStreamToString(inputStream) : "false";
//            } catch (Exception e) {
//                Log.d("InputStream", ""+e.getLocalizedMessage());
//                Log.d("InputStream", ""+e.toString());
//                result = "false";
//            }
//
//            return result;
//        }
//        // onPostExecute displays the results of the AsyncTask.
//        @Override
//        protected void onPostExecute(String result) {
//            if(result != "false" && result != "") {
//                try {
//                    JSONArray items = new JSONArray(result);
//
//                    for(int i=0;i<items.length();i++){
//                        try{
//                            JSONObject item = new JSONObject(items.get(i).toString());
//
//                            Drawable img = LoadImageFromWebOperations(item.getString("image"));
//                            //Drawable img2 = LoadImageFromWebOperations("http://images.all-free-download.com/images/wallpapers_large/frozen_heart_wallpaper_3d_models_3d_wallpaper_58.jpg");
//
//                            LayoutInflater vi = (LayoutInflater) getBaseContext().getSystemService(getBaseContext().LAYOUT_INFLATER_SERVICE);
//                            View v = vi.inflate(R.layout.item, null);
//                            TextView name = (TextView) v.findViewById(R.id.name);
//                            name.setText(item.getString("name"));
////                            v.setBackground(img);
//                            ImageView imgView = (ImageView) v.findViewById(R.id.img);
//                            imgView.setImageDrawable(img);
//                            TableLayout table = (TableLayout) findViewById(R.id.table);
//                            table.addView(v);
//                        }catch (Exception e) {
//                            Log.d("error", e.toString());
//                        }
//                    }
//
//                } catch (Exception e) {
//                    Log.d("error", e.toString());
//                }
//            }
//        }
//    }
//    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
//        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
//        String line = "";
//        String result = "";
//        while((line = bufferedReader.readLine()) != null)
//            result += line;
//
//        inputStream.close();
//        return result;
//
//    }

    private Bitmap urlImageToBitmap(String imageUrl) throws Exception {
        Bitmap result = null;
        URL url = new URL(imageUrl);
        if(url != null) {
            result = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        return result;
    }
}
