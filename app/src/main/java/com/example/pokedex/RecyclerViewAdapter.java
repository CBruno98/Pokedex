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
    private Pokemon poke;
    private Result pokeR;


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
    public ArrayList<Result> pokemonArrayList;


    public RecyclerViewAdapter(Context context, List<Pokemon> pokemonList) {
        this.mContext = context;
        this.pokemonsList = pokemonList;
    }
    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.pokemonArrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //poke = pokemonsList.get(position);
        pokeR = pokemonArrayList.get(position);
        final String urlStr = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(position+1)+".png";
        final String pokeName = pokeR.getName();
        holder.mPokemonName.setText(pokeName);
        /*final int baseExp = poke.getBase_experience();
        final int height = poke.getHeight();
        final int weight = poke.getWeight();*/
        Glide.with(mContext)
                .load(urlStr)
                .into(holder.mPokemonImage);
        /*holder.mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailedPokemon.class);
                intent.putExtra("Name", poke.getName());
                intent.putExtra("Exp", baseExp);
                intent.putExtra("Height", height);
                intent.putExtra("Weight", weight);
                intent.putExtra("Img", urlStr);
                mContext.startActivity(intent);
            }
        });*/
        holder.mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailedPokemon.class);
                intent.putExtra("position",position+1);
                intent.putExtra("url", urlStr);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    public void agregarLista(ArrayList<Result> listaPokemon){
        pokemonArrayList.addAll(listaPokemon);
        notifyDataSetChanged();
    }
}
