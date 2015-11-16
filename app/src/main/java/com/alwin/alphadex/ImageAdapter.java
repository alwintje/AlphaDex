package com.alwin.alphadex;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Bitmap> bitmapList;

    public ImageAdapter(Context context, ArrayList<Bitmap> bitmapList) {
        this.context = context;
        this.bitmapList = bitmapList;
    }

    public int getCount() {
        return this.bitmapList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {


//        SquareImageView imageView;

        View p = convertView;
        if (p == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            p = inflater.inflate(R.layout.item, parent, false);
        }

//        SquareImageView imageView = (SquareImageView) p.findViewById(R.id.img);
        MLRoundedImageView imageView = (MLRoundedImageView) p.findViewById(R.id.img);

        TextView name = (TextView) p.findViewById(R.id.name);

//        imageView = new SquareImageView(this.context);

//        imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        name.setText("Test");
        imageView.setImageBitmap(this.bitmapList.get(position));

        return p;
    }

}


//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.TableLayout;
//import android.widget.TextView;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
//public class ImageAdapter extends BaseAdapter {
//    private Context mContext;
//    private Integer[] mThumbIds;
//    private Drawable[] thumbs;
//
//    public ImageAdapter(Context c) {
//        InputStream inputStream;
//        String result = "";
//        try {
//            URL url = new URL(MainActivity.websiteAdres + "?items");
//            URLConnection urlConnection = url.openConnection();
//            inputStream = new BufferedInputStream(urlConnection.getInputStream());
//            result = inputStream != null ? convertInputStreamToString(inputStream) : "false";
//
//        } catch (Exception e) {
//            Log.d("InputStream", "" + e.getLocalizedMessage());
//            Log.d("InputStream", ""+e.toString());
//            result = "false";
//        }
//
//        if(result != "false" && result != "") {
//            try {
//                JSONArray items = new JSONArray(result);
//
//                for(int i=0;i<items.length();i++){
//                    try{
//                        JSONObject item = new JSONObject(items.get(i).toString());
//
//                        Drawable img = LoadImageFromWebOperations(item.getString("image"));
//                        thumbs[thumbs.length] = img;
//
//                    }catch (Exception e) {
//                        Log.d("error", e.toString());
//                    }
//                }
//
//            } catch (Exception e) {
//                Log.d("error", e.toString());
//            }
//        }
//        mContext = c;
//    }
//
//    public int getCount() {
////        return mThumbIds.length;
//        return 5;
//    }
//
//    public Object getItem(int position) {
//        return null;
//    }
//
//    public long getItemId(int position) {
//        return 0;
//    }
//
//
//    // create a new ImageView for each item referenced by the Adapter
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//
//        while (thumbs == ){
//
//        }
//        if (convertView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);
//        } else {
//            imageView = (ImageView) convertView;
//        }
//
//        //imageView.setImageResource(thumbs[position]);
//        if(thumbs.length < position){
//            imageView.setImageDrawable(thumbs[position]);
//        }
//
//        return imageView;
//    }
//
//    // references to our images
////    private Integer[] mThumbIds = {
////            R.drawable.sample_2, R.drawable.sample_3,
////            R.drawable.sample_4, R.drawable.sample_5,
////            R.drawable.sample_6, R.drawable.sample_7,
////            R.drawable.sample_0, R.drawable.sample_1,
////            R.drawable.sample_2, R.drawable.sample_3,
////            R.drawable.sample_4, R.drawable.sample_5,
////            R.drawable.sample_6, R.drawable.sample_7,
////            R.drawable.sample_0, R.drawable.sample_1,
////            R.drawable.sample_2, R.drawable.sample_3,
////            R.drawable.sample_4, R.drawable.sample_5,
////            R.drawable.sample_6, R.drawable.sample_7
////    };
//
//
////    private class getItems extends AsyncTask<String, Void, String> {
//////        @Override
//////        protected String doInBackground(String... urls) {
//////
//////
//////            return result;
//////        }
////        // onPostExecute displays the results of the AsyncTask.
////        @Override
////        protected void onPostExecute(String result) {
////        }
////    }
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
//
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
//}