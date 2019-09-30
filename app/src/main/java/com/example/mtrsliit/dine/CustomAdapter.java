package com.example.mtrsliit.dine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<UserModel> userModelArrayList;

    public CustomAdapter(Context context, ArrayList<UserModel> userModelArrayList) {

        this.context = context;
        this.userModelArrayList = userModelArrayList;
    }


    @Override
    public int getCount() {
        return userModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvemail = (TextView) convertView.findViewById(R.id.email);
            holder.tvphone = (TextView) convertView.findViewById(R.id.phone);
            holder.tvpswd = (TextView) convertView.findViewById(R.id.pwsd);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText("Name: "+userModelArrayList.get(position).getName());
        holder.tvemail.setText("Email: "+userModelArrayList.get(position).getEmail());
        holder.tvphone.setText("Phone: "+userModelArrayList.get(position).getPhone());
        holder.tvpswd.setText("password: "+userModelArrayList.get(position).getPassword());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname, tvemail,tvphone,tvpswd;
    }

}
