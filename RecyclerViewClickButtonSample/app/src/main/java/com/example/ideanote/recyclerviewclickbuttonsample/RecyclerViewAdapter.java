package com.example.ideanote.recyclerviewclickbuttonsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<String> data;

    public RecyclerViewAdapter(Context context, ArrayList<String> data) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton imageButton;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageButton = (ImageButton) itemView.findViewById(R.id.image_button);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // buttonを押したときの処理
                    Toast.makeText(itemView.getContext(), "IMAGE BUTTON", Toast.LENGTH_LONG).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // viewを押したときの処理
                    Toast.makeText(itemView.getContext(), "VIEW CLICKED", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
