package com.dandd.kitapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter {
    private final Activity context;
    private final Integer[] imageIDarray;
    private final String[] infoArray;
    public CustomListAdapter(Activity context, String[] infoArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.listview , infoArrayParam);
        this.context = context;

        this.imageIDarray = imageIDArrayParam;
        this.infoArray = infoArrayParam;
    }
    public CustomListAdapter(Activity context, String[] nameArrayParam, String[] infoArrayParam, Integer[] imageIDArrayParam){

        super(context,R.layout.listview, nameArrayParam);

        this.context=context;
        this.imageIDarray = imageIDArrayParam;

        this.infoArray = infoArrayParam;

    }
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview, null,true);

        //this code gets references to objects in the listview_row.xml file

        TextView infoTextField = (TextView) rowView.findViewById(R.id.desp);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.thumb);

        //this code sets the values of the objects to values from the arrays

        infoTextField.setText(infoArray[position]);
        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    };
}


