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
    Pokemon poke;


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mPokemonName;
        private ImageView mPokemonImage;
        private LinearLayout mParentView;

        public ViewHolder(View itemView) {
            super(itemView);
            mPokemonName = (TextView) itemView.findViewById(R.id.pokemon_name_recycler);
            mPokemonImage = (ImageView) itemView.findViewById(R.id.pokemon_sprite_recycler);
            mParentView = (LinearLayout) itemView.findViewById(R.id.recycler_view_parent);
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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        poke = pokemonsList.get(position);
        final String urlStr = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(position+1)+".png";
        final String pokeName = poke.getName();
        holder.mPokemonName.setText(pokeName);
        Glide.with(mContext)
                .load(urlStr)
                .into(holder.mPokemonImage);
        holder.mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailedPokemon.class);
                intent.putExtra("Name", pokeName);
                intent.putExtra("Exp", poke.getBase_experience());
                intent.putExtra("Height", poke.getHeight());
                intent.putExtra("Weight", poke.getWeight());
                intent.putExtra("Img", urlStr);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonsList.size();
    }
}
