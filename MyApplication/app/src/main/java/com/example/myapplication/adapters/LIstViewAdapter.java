package com.example.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.API.UserList;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LIstViewAdapter extends ArrayAdapter<UserList.User> {
    private Context mcontext;
    private int mresource;
    public LIstViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<UserList.User> objects) {
        super(context, resource, objects);
        this.mcontext=context;
        this.mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mresource,parent,false);

        TextView nameTxt = (TextView) convertView.findViewById(R.id.item_name);
        TextView emailTxt = (TextView) convertView.findViewById(R.id.item_email);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.item_image);

        Picasso.get().load(getItem(position).avatar).into(imageView);
        nameTxt.setText(getItem(position).getFullName());
        emailTxt.setText(getItem(position).email);


        return convertView;
    }
}
