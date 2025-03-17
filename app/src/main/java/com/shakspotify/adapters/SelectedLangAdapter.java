package com.shakspotify.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shakspotify.R;

import java.util.List;

public class SelectedLangAdapter extends RecyclerView.Adapter<SelectedLangAdapter.SelectedLangViewHolder> {

    private final List<String> selectedLanguages;
    Context context;

    public SelectedLangAdapter(List<String> selectedLanguages, Context context) {
        this.selectedLanguages = selectedLanguages;
        this.context = context;
    }


    @Override @NonNull
    public SelectedLangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.selected_languages_layout, parent, false);
        return new SelectedLangViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedLangViewHolder holder, int position) {
        holder.langNameTxt.setText(selectedLanguages.get(position));
        if (position == 0){
            holder.langNameTxt.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_green_bg));
        } else {
            holder.langNameTxt.setBackground(ContextCompat.getDrawable(context, R.drawable.rounded_stroked_bg));
        }
    }

    @Override
    public int getItemCount() {
        return selectedLanguages.size();
    }

    public static class SelectedLangViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView langNameTxt;
        public SelectedLangViewHolder(@NonNull View itemView) {
            super(itemView);
            langNameTxt = itemView.findViewById(R.id.langNameTxt);
        }
    }
}
