package com.example.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewPokemon;
    private RecyclerViewAdapter adapterPokemon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerViewPokemon = findViewById(R.id.recycler_view);
        recyclerViewPokemon.setLayoutManager(new LinearLayoutManager(this));

        /* adapterPokemon = new RecyclerViewAdapter(this, getPokemons());
        recyclerViewPokemon.setAdapter(adapterPokemon);*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAPI api = retrofit.create(RestAPI.class);

        Call<PokemonFeed> call = api.getData();

        call.enqueue(new Callback<PokemonFeed>() {
            @Override
            public void onResponse(Call<PokemonFeed> call, Response<PokemonFeed> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(PokemonListActivity.this,"F - Response",Toast.LENGTH_SHORT).show();
                    return;
                }
                PokemonFeed data = response.body();

            }

            @Override
            public void onFailure(Call<PokemonFeed> call, Throwable t) {
                Toast.makeText(PokemonListActivity.this,"F - Failure",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
