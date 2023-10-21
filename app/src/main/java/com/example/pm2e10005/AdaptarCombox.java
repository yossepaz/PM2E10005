package com.example.pm2e10005;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdaptarCombox extends BaseAdapter {
    private Context context;
    private String [] array;
    private int posicion;

    public AdaptarCombox(Context context, String[] array) {
        this.context = context;
        this.array = array;
    }

    @Override
    public int getCount() {
        return array.length;
    }

    @Override
    public Object getItem(int i) {
        return array[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.combobox, null);
        TextView contenido = v.findViewById(R.id.contenido);
        contenido.setText((String)getItem(i));
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                posicion = i;
                return false;
            }
        });
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        View v =  super.getDropDownView(position, convertView, parent);
        if (posicion == position){
            v.setBackgroundResource(R.color.design_default_color_primary);
            TextView tv = v.findViewById(R.id.contenido);
            tv.setTextColor(Color.BLACK);
        }
        return v;
    }



    

}
