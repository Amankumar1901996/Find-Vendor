package com.example.findvendor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RouteLocationAdapter extends RecyclerView.Adapter<RouteLocationAdapter.ViewHolder> {
    Context context;
    ArrayList<VendorRouteModel> arrlocations;
    RouteLocationAdapter(Context context, ArrayList<VendorRouteModel> arrlocations){
        this.context = context;
        this.arrlocations = arrlocations;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.route_templete, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.locationtext.setText(arrlocations.get(position).location);
        holder.displaytimetxt.setText(arrlocations.get(position).time);


    }

    @Override
    public int getItemCount() {
        return arrlocations.size();
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
