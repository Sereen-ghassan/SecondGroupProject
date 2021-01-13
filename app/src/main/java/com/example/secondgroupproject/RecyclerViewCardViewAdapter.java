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
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class RecyclerViewCardViewAdapter extends RecyclerView.Adapter<RecyclerViewCardViewAdapter.ViewHolder> {

    Context context;

    ArrayList<Car> cars;
    ImageView ivResult;
    public RecyclerViewCardViewAdapter(Context context, ArrayList<Car> getDataAdapter) {

        super();

        this.cars = getDataAdapter;

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

        Car getDataAdapter1 = cars.get(position);

        holder.SubjectName.setText(getDataAdapter1.getNameCar());


        CardView cardView = holder.cardView;
         ivResult = cardView.findViewById(R.id.image);
        LaodImage laodImage = new LaodImage(ivResult);
        laodImage.execute(getDataAdapter1.getImageID());
////        Drawable dr = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
////        imageView.setImageDrawable(dr);
//
//        TextView txt = (TextView) cardView.findViewById(R.id.Name);
//        txt.setText(captions[position]);
        cardView.setOnClickListener((card) -> {
            Intent intent = new Intent(card.getContext(), cardDetails.class);
            intent.putExtra("Id", position);
            MainActivity.context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {

        return cars.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView SubjectName;
        private CardView cardView;
        private ImageView imageView;
        public ViewHolder(CardView cardView) {

            super(cardView);
            this.cardView=cardView;

            SubjectName = (TextView) itemView.findViewById(R.id.TextViewCard);
             imageView = cardView.findViewById(R.id.image);


        }

    }
    private class  LaodImage extends AsyncTask<String,Void, Bitmap>{

        ImageView imageView;
        public LaodImage (ImageView ivResult){
            this.imageView = ivResult;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            String urlLink = strings[0];
            Bitmap bitmap = null;
             try {
                 InputStream inputStream=  new java.net.URL(urlLink).openStream();
                 bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivResult.setImageBitmap(bitmap);
        }
    }
}