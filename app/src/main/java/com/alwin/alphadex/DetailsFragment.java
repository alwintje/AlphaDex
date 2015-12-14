package com.alwin.alphadex;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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

/**
 * Created by Alwin on 17-11-2015.
 */

public class DetailsFragment extends Fragment {



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.d("fragment", "started");

        String item = (String) getActivity().getIntent().getExtras().get("item");
        Log.d("Item_id", item);
        new getItem().execute(Integer.parseInt(item));

//        setHasOptionsMenu(true);
//        this.getView().setFocusableInTouchMode(true);
//        this.getView().requestFocus();
//        this.getView().setOnKeyListener( new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    LinearLayout gridList = (LinearLayout) getActivity().findViewById(R.id.grid_list);
//                    gridList.setVisibility(View.INVISIBLE);
//
//                    FrameLayout relativeDetail = (FrameLayout) getActivity().findViewById(R.id.details);
//                    relativeDetail.setVisibility(View.GONE);
//
//                    setHasOptionsMenu(false);
//
//                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    return true;
//                }
//                return false;
//            }
//        });
    }
    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.details_fragment, container, false);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here

//        getActivity().getMenuInflater().inflate(R.menu.details, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    private class getItem extends AsyncTask<Object, Void, Boolean> {

        Bitmap img;
        String name;
        String eye_color;
        String hair_color;
        String birth;

        @Override
        protected Boolean doInBackground(Object... urls) {


//            bitmapList = new ArrayList<Bitmap>();
            Integer item = (Integer) urls[0];
            Boolean good = false;
            try {

                InputStream inputStream;
                String result = "";
                try {
                    URL url = new URL(MainActivity.websiteAdres + "?id=" + String.valueOf(item));

                    URLConnection urlConnection = url.openConnection();
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    result = inputStream != null ? MainActivity.convertInputStreamToString(inputStream) : "false";

                } catch (Exception e) {
                    Log.d("InputStream", "" + e.getLocalizedMessage());
                    Log.d("InputStream", ""+e.toString());
                    result = "false";
                }
                if(result != "false" && result != "") {
                    JSONObject itemObject = new JSONObject(result);
//                    Log.d("img",itemObject.getString("image"));
//                    Log.d("img",String.valueOf(itemObject));
//                    Log.d("url",MainActivity.websiteAdres + "?id=" + String.valueOf(item));
                    Bitmap image = MainActivity.urlImageToBitmap(itemObject.getString("image"));
                    name = itemObject.getString("name");
                    eye_color = itemObject.getString("eye_color");
                    hair_color = itemObject.getString("hair_color");
                    birth = itemObject.getString("birth");
                    img = image;
                    good = true;
                }
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
            return good;
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(Boolean done) {
            if(done){
//                if(swipeLayout){
//                    swipe.setRefreshing(false);
//                }
//                grid.removeAllViewsInLayout();
//                grid.setAdapter(new ImageAdapter(getBaseContext(), bitmapList, namesList));
//                ProgressBar loader = (ProgressBar) findViewById(R.id.loader);
//                loader.setVisibility(View.GONE);
//                ImageView imgView = (ImageView) getActivity().findViewById(R.id.header_bg_image);
//                imgView.setImageBitmap(blur(img));
                RelativeLayout header = (RelativeLayout) getActivity().findViewById(R.id.details_header);
                RoundedImageView headerImage = (RoundedImageView) header.findViewById(R.id.header_image);

//                Bitmap normalImage = img;
                headerImage.setImageBitmap(img);

                Drawable headerBg = new BitmapDrawable(blur(img.copy(img.getConfig(),true)));
                header.setBackground(headerBg);

                TextView nameText = (TextView) getActivity().findViewById(R.id.name);
                nameText.setText(name);
                Log.d("name", name);

                TextView eye = (TextView) getActivity().findViewById(R.id.eye_color);
                eye.setText(eye_color);
                TextView hair = (TextView) getActivity().findViewById(R.id.hair_color);
                hair.setText(hair_color);
                TextView birthText = (TextView) getActivity().findViewById(R.id.birth);
                birthText.setText(birth);

            }
        }
    }
    private static final float BLUR_RADIUS = 25f;

    public Bitmap blur(Bitmap image) {
        if (null == image) return null;

        Bitmap outputBitmap = Bitmap.createBitmap(image);
        final RenderScript renderScript = RenderScript.create(this.getActivity());
        Allocation tmpIn = Allocation.createFromBitmap(renderScript, image);
        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

        //Intrinsic Gausian blur filter
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

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


//    private Bitmap urlImageToBitmap(String imageUrl) throws Exception {
//        Bitmap result = null;
//        URL url = new URL(imageUrl);
//        if(url != null) {
//            result = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//        }
//        return result;
//    }
    //    @Override
//    public void onBackStackChanged() {
//
//        int backStackEntryCount = getFragmentManager().getBackStackEntryCount();
//
//        if(backStackEntryCount > 0){
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }else{
//            getActionBar().setDisplayHomeAsUpEnabled(false);
//        }
//    }
}
