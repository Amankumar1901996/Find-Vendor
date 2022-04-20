package com.example.findvendor;


import static android.media.CamcorderProfile.get;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;

public class RouteLocationAdapter extends FirebaseRecyclerAdapter<VendorRouteModel,RouteLocationAdapter.ViewHolder> {
    Context context;
    int row_index = -1;
    private Handler handler;
    boolean statusAnimation = false;




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

        //VendorRouteModel temp = .get(position);
        handler = new Handler();

        holder.locationtext.setText(model.getLocation());
        holder.displaytimetxt.setText(model.getTime());



        holder.locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("clicked : ", model.getLocation());
                if (statusAnimation) {
                    stopPulse();
                }
                else {
                    startPulse();
                }
                statusAnimation = !statusAnimation;
            }

            private void startPulse() {

                this.runnable.run();
            }

            private void stopPulse() {

                handler.removeCallbacks(runnable);

            }

            private Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    holder.image1.animate().scaleX(1.5f).scaleY(1.5f).alpha(0f).setDuration(1000)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    holder.image1.setScaleX(1f);
                                    holder.image1.setScaleY(1f);
                                    holder.image1.setAlpha(1f);
                                }
                            });

                    holder.image2.animate().scaleX(1.5f).scaleY(1.5f).alpha(0f).setDuration(700)
                            .withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    holder.image2.setScaleX(1f);
                                    holder.image2.setScaleY(1f);
                                    holder.image2.setAlpha(1f);
                                }
                            });

                    handler.postDelayed(runnable, 1500);
                }
            };

        });







    }


    /**public interface OnItemClickListener {
        public void onClick(View view, int position);
    }**/


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView locationtext, displaytimetxt;
        Button locationbtn;
        ImageView image1,image2;



        public ViewHolder(View itemView){
            super(itemView);
            locationtext = itemView.findViewById(R.id.places);
            displaytimetxt = itemView.findViewById(R.id.displaytimetxt);
            locationbtn = itemView.findViewById(R.id.locationbtn);
            image1 = itemView.findViewById(R.id.imgAnimation1);
            image2 = itemView.findViewById(R.id.imgAnimation2);


        }
    }

}




/**  Button btn;
    ImageView img1,img2;
    private Handler handler;
    boolean statusAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        handler = new Handler();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "button clicked" , Toast.LENGTH_LONG).show();
                if (statusAnimation) {
                    stopPulse();
                }
                else {
                    startPulse();
                     }
                statusAnimation = !statusAnimation;
            }
        });
    }

    private void startPulse() {
        Toast.makeText(MainActivity.this, "stoped clicked" , Toast.LENGTH_LONG).show();
        this.runnable.run();
    }

    private void stopPulse() {
        Toast.makeText(MainActivity.this, "start clicked" , Toast.LENGTH_LONG).show();
        handler.removeCallbacks(runnable);

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            img1.animate().scaleX(1.5f).scaleY(1.5f).alpha(0f).setDuration(1000)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            img1.setScaleX(1f);
                            img1.setScaleY(1f);
                            img1.setAlpha(1f);
                        }
                    });

            img2.animate().scaleX(1.5f).scaleY(1.5f).alpha(0f).setDuration(700)
                    .withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            img2.setScaleX(1f);
                            img2.setScaleY(1f);
                            img2.setAlpha(1f);
                        }
                    });

            handler.postDelayed(runnable, 1500);
        }
    };
}
**/