package com.example.minimo2prueba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<User> values;


    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public View layout;
        public ImageView avatar;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            name = (TextView) v.findViewById(R.id.rowNameText);
            avatar = (ImageView) v.findViewById(R.id.iconImage);
        }
    }

    public void add(int position, User item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(List<User> myDataset) {
        values = myDataset;
        LayoutInflater inflater;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = values.get(position).getLogin();
        holder.name.setText(name);
        Context context = holder.avatar.getContext();
        Picasso.with(context).load(values.get(position).getAvatar_url()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}
