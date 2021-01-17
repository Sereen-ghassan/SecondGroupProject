package com.example.secondgroupproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class locationAdapter extends RecyclerView.Adapter<locationAdapter.ViewHolder> {

    Context context;

    ArrayList<Locality> localities;
    public locationAdapter(Context context, ArrayList<Locality> getDataAdapter) {

        super();

        this.localities = getDataAdapter;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);

        ViewHolder viewHolder = new ViewHolder((CardView) view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CardView cardView = holder.cardView;

        Locality getDataAdapter1 = localities.get(position);

        holder.SubjectPrice.setText(getDataAdapter1.getPrice());
        holder.SubjectSurface.setText(getDataAdapter1.getSurface());
        holder.SubjectLocalityType.setText(getDataAdapter1.getLocalityType());

        Glide.with(context).load(getDataAdapter1.getImageID()).into(holder.imageView);
        cardView.setOnClickListener((card) -> {
            Intent intent = new Intent(card.getContext(), cardLocation.class);
            intent.putExtra("Id", position);
            veiwUponLocation.context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {

        return localities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView SubjectPrice;
        public TextView SubjectSurface;
        public TextView SubjectLocalityType;
        private CardView cardView;
        private ImageView imageView;
        public ViewHolder(CardView cardView) {

            super(cardView);
            this.cardView=cardView;

            SubjectPrice = (TextView) itemView.findViewById(R.id.TextViewCard);
            SubjectSurface = (TextView) itemView.findViewById(R.id.TextViewCard2);
            SubjectLocalityType = (TextView) itemView.findViewById(R.id.TextViewCard3);
            imageView = cardView.findViewById(R.id.image);


        }

    }
}