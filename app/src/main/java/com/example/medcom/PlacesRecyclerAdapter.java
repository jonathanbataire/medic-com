package com.example.medcom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlacesRecyclerAdapter extends RecyclerView.Adapter<PlacesRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    private ArrayList<Place> placesArrayList;
    private final LayoutInflater mLayoutInflater;
    private int currentPosition;

    public PlacesRecyclerAdapter(Context context, ArrayList<Place> placesData){
        mContext = context;
        this.placesArrayList = placesData;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.place_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        currentPosition = position;
        holder.name.setText(placesArrayList.get(position).getName());
        holder.vicinity.setText(placesArrayList.get(position).getVicinity());
        Picasso.with(mContext).load(placesArrayList.get(position).getIcon()).fit().centerCrop()
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return placesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView vicinity;
        private final TextView name;
        private final ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            vicinity = itemView.findViewById(R.id.vicinity);
            image = itemView.findViewById(R.id.image);
        }
    }
}
