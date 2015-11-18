package com.alwin.alphadex;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Bitmap> bitmapList;
    private ArrayList<String> namesList;


    public ImageAdapter(Context context, ArrayList<Bitmap> bitmapList, ArrayList<String> namesList) {
        this.context = context;
        this.bitmapList = bitmapList;
        this.namesList = namesList;
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

        View p = convertView;
        if (p == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            p = inflater.inflate(R.layout.item, parent, false);
        }

//        RoundedImageView imageView = (RoundedImageView) p.findViewById(R.id.img);
//        MLRoundedImageView imageView = (MLRoundedImageView) p.findViewById(R.id.img);
        MLRoundedImageView imageView = (MLRoundedImageView) p.findViewById(R.id.img);

        TextView name = (TextView) p.findViewById(R.id.name);

        name.setText(this.namesList.get(position));
        imageView.setImageBitmap(this.bitmapList.get(position));


        return p;
    }



}
