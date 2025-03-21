package com.shakspotify.adapters;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.shakspotify.R;
import com.shakspotify.models.MusicLangItemModel;

import java.util.List;

public class MusicLangAdapter extends RecyclerView.Adapter<MusicLangAdapter.MusicLangViewHolder> {

    private final List<MusicLangItemModel> musicLangItemsList;
    private final Context context;


    public MusicLangAdapter(List<MusicLangItemModel> musicLangItemsList, Context context) {
        this.musicLangItemsList = musicLangItemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MusicLangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.music_lang_item_model_layout,parent, false);
        return new MusicLangViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicLangViewHolder holder, int position) {
        MusicLangItemModel musicLangItemModel = musicLangItemsList.get(position);
        holder.musicLangNameTxt.setText(musicLangItemModel.getLangName());
        holder.musicLangArtistImg.setImageResource(musicLangItemModel.getArtistImageId());

        // Create a dynamic gradient background
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.BL_TR, // Diagonal gradient
                new int[]{musicLangItemModel.getBgStartColor(), musicLangItemModel.getBgEndColor()}
        );

        holder.musicLangClay.setBackground(gradientDrawable);

        holder.musicLangCheckbox.setChecked(musicLangItemModel.getSelected());

        holder.musicLangClay.setOnClickListener(v -> {
            boolean newState = !musicLangItemModel.getSelected(); // Toggle the state on click
            musicLangItemModel.setSelected(newState);
            holder.musicLangCheckbox.setChecked(newState);
        });
    }

    @Override
    public int getItemCount() {
        return musicLangItemsList.size();
    }

    public static class MusicLangViewHolder extends RecyclerView.ViewHolder{
        private final ConstraintLayout musicLangClay;
        private final AppCompatTextView musicLangNameTxt;
        private final AppCompatCheckBox musicLangCheckbox;
        private final AppCompatImageView musicLangArtistImg;

        public MusicLangViewHolder(@NonNull View itemView) {
            super(itemView);
            musicLangClay = itemView.findViewById(R.id.musicLangClay);
            musicLangNameTxt = itemView.findViewById(R.id.musicLangNameTxt);
            musicLangCheckbox = itemView.findViewById(R.id.musicLangCheckbox);
            musicLangArtistImg = itemView.findViewById(R.id.musicLangArtistImg);
        }
    }
}
