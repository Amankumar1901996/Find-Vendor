package com.example.findvendor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

public class RouteLocationAdapter extends FirebaseRecyclerAdapter<VendorRouteModel,RouteLocationAdapter.ViewHolder> {
    Context context;
    public RouteLocationAdapter(@NonNull FirebaseRecyclerOptions<VendorRouteModel> options) {
        super(options);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.route_templete, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull VendorRouteModel model) {
        holder.locationtext.setText(model.location);
        holder.displaytimetxt.setText(model.time);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView locationtext, displaytimetxt;

        public ViewHolder(View itemView){
            super(itemView);
            locationtext = itemView.findViewById(R.id.places);
            displaytimetxt = itemView.findViewById(R.id.displaytimetxt);
        }
    }
}
