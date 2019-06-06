package com.example.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPokemon;
    private RecyclerViewAdapter adapterPokemon;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pokemon_rv);

        recyclerViewPokemon = findViewById(R.id.recycler_view);
        recyclerViewPokemon.setLayoutManager(new LinearLayoutManager(this));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        adapterPokemon = new RecyclerViewAdapter(this);
        recyclerViewPokemon.setAdapter(adapterPokemon);

        getData();



    }

    public void getData(){
        final RestAPI serviceAPI = retrofit.create(RestAPI.class);
        final Call<PokemonFeed> respuestaPokemons = serviceAPI.obtenerListaPokemon();

        respuestaPokemons.enqueue(new Callback<PokemonFeed>() {
            @Override
            public void onResponse(Call<PokemonFeed> call, Response<PokemonFeed> response) {
                if (response.isSuccessful()) {
                    PokemonFeed respuestaPokemon = response.body();
                    ArrayList<Result> listaPokemon = respuestaPokemon.getResults();
                    adapterPokemon.agregarLista(listaPokemon);
                    respuestaPokemons.cancel();
                }else {
                    Log.e("Error: ", "on Response " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonFeed> call, Throwable t) {
                Log.e("Error: ", "onFailure" + t.getMessage());
            }
        });
    }
}
