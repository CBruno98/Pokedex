package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Result> list;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mPokemonName;
        private ImageView mPokemonImage;
        private LinearLayout mParentView;

        public ViewHolder(View itemView) {
            super(itemView);
            mPokemonName = (TextView) itemView.findViewById(R.id.pokemon_name_recycler);
            mPokemonImage = (ImageView) itemView.findViewById(R.id.pokemon_sprite_recycler);
            mParentView = itemView.findViewById(R.id.recycler_view_parent);
        }
    }

    public List<Pokemon> pokemonsList;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<Pokemon> pokemonList) {
        this.mContext = context;
        this.pokemonsList = pokemonList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String pokeName = pokemonsList.get(position).getName();
        holder.mPokemonName.setText(pokeName);
        Glide.with(mContext)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(position+1)+".png")
                .into(holder.mPokemonImage);
        /*holder.mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Viewer.class);
                intent.putExtra("title", fightTV);
                intent.putExtra("description", descTV);
                intent.putExtra("img_id", id);
                mContext.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return pokemonsList.size();
    }
}
