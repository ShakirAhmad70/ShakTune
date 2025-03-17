package com.shakspotify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.shakspotify.R;
import com.shakspotify.models.ArtistsItemModel;

import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistsViewHolder> {

    List<ArtistsItemModel> artistsList;
    Context context;

    public ArtistsAdapter(List<ArtistsItemModel> artistsList, Context context) {
        this.artistsList = artistsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ArtistsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.artists_item_model_layout, parent, false);
        return new ArtistsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistsViewHolder holder, int position) {
        ArtistsItemModel artistsItemModel = artistsList.get(position);
        holder.artistImg.setImageResource(artistsItemModel.getArtistImg());
        holder.artistNameTxt.setText(artistsItemModel.getArtistName());
        holder.artistCheckbox.setChecked(artistsItemModel.isSelected());

        holder.artistItemRLay.setOnClickListener(v -> {
            boolean newState = !artistsItemModel.isSelected(); // Toggle the state on click
            artistsItemModel.setSelected(newState);
            holder.artistCheckbox.setChecked(newState);
        });
    }

    @Override
    public int getItemCount() {
        return artistsList.size();
    }

    public static class ArtistsViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView artistImg;
        AppCompatTextView artistNameTxt;
        AppCompatCheckBox artistCheckbox;
    RelativeLayout artistItemRLay;

        public ArtistsViewHolder(@NonNull View itemView) {
            super(itemView);
            artistCheckbox = itemView.findViewById(R.id.artistCheckbox);
            artistImg = itemView.findViewById(R.id.artistImg);
            artistNameTxt = itemView.findViewById(R.id.artistNameTxt);
            artistItemRLay = itemView.findViewById(R.id.artistItemRLay);
        }
    }
}
