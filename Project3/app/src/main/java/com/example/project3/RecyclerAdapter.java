package com.example.project3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AdapterHolder> {
    String data1[],data2[],data3[];
    int images[];
    Context context;
    public RecyclerAdapter(Context cont, String d1[],String d2[],String d3[],int img[]){
        context = cont;
        data1 = d1;
        data2 = d2;
        data3 = d3;
        images = img;
    }
    @NonNull
    @Override
    public RecyclerAdapter.AdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.line,parent,false);

        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.AdapterHolder holder, int position) {
        holder.text1.setText(data1[position]);
        holder.text2.setText(data2[position]);
        holder.text3.setText(data3[position]);
        holder.png.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class AdapterHolder extends RecyclerView.ViewHolder{

        TextView text1,text2,text3;
        ImageView png;

        public AdapterHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.name);
            text2 = itemView.findViewById(R.id.desc);
            text3 = itemView.findViewById(R.id.price);
            png = itemView.findViewById(R.id.image);
        }
    }
}
