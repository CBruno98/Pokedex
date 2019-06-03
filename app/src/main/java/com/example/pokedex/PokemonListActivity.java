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
    private RestAPI jsonPlaceHolderApi;
    List<Pokemon> listOfPokemons = new ArrayList<Pokemon>();
    private Sprites SpriteObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_pokemon_rv);

        recyclerViewPokemon = findViewById(R.id.recycler_view);
        recyclerViewPokemon.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi =retrofit.create(RestAPI.class);

        getPokemon();

    }

    public void getPokemon(){
        for (int i=1;i<10;i++) {
            Call<Pokemon> call = jsonPlaceHolderApi.getData(i);
            Log.v("Conexion -", "Conectando para indice"+i);
            call.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(PokemonListActivity.this, "F - Connection: ", Toast.LENGTH_SHORT).show();
                        Log.v("////Error en indice///","F");
                        return;
                    }
                    Pokemon pokeObject = response.body();
                    listOfPokemons.add(pokeObject);
                    recyclerViewPokemon.setAdapter(new RecyclerViewAdapter(PokemonListActivity.this,listOfPokemons));
                    Log.v("Respuesta: ", pokeObject.getName()+ "   "+pokeObject.getHeight()+"   "+pokeObject.getWeight());
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Toast.makeText(PokemonListActivity.this, "F - Failure", Toast.LENGTH_SHORT).show();
                    Log.v("Error: ", "en CALLBACK");
                }
            });
        }

    }

    public void showData(List<Pokemon> pokeList){

        recyclerViewPokemon.setAdapter(adapterPokemon);
    }
}
