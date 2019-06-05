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
    private List<Pokemon> listOfPokemons = new ArrayList<Pokemon>();
    private Sprites SpriteObj;
    private int cont=1;
    Retrofit retrofit;

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
        //getPokemon(retrofit);
        getData();



    }

    public void getPokemon(Retrofit retrofit){
        for (int i=1;i<10;i++) {
            jsonPlaceHolderApi =retrofit.create(RestAPI.class);
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
                    cont++;
                    if (cont>=10) {recyclerViewPokemon.setAdapter(new RecyclerViewAdapter(PokemonListActivity.this, listOfPokemons));}
                    Log.v("Respuesta: ", pokeObject.getName()+ "   "+pokeObject.getHeight()+"   "+pokeObject.getWeight());
                    Log.v("Cont: ", String.valueOf(cont));
                    return;
                }

                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {
                    Toast.makeText(PokemonListActivity.this, "F - Failure", Toast.LENGTH_SHORT).show();
                    Log.v("Error: ", "en CALLBACK");
                }
            });
        }

    }

    public void getPokemons(Pokemon pokeObject){
        if (cont < 10){
            listOfPokemons.add(pokeObject);
            cont++;
        }
        else {
            listOfPokemons.add(pokeObject);
            recyclerViewPokemon.setAdapter(new RecyclerViewAdapter(PokemonListActivity.this, listOfPokemons));
        }
    }

    public void getData(){
        RestAPI serviceAPI = retrofit.create(RestAPI.class);
        Call<PokemonFeed> respuestaPokemons = serviceAPI.obtenerListaPokemon();

        respuestaPokemons.enqueue(new Callback<PokemonFeed>() {
            @Override
            public void onResponse(Call<PokemonFeed> call, Response<PokemonFeed> response) {
                if (response.isSuccessful()) {
                    PokemonFeed respuestaPokemon = response.body();
                    ArrayList<Result> listaPokemon = respuestaPokemon.getResults();
                    adapterPokemon.agregarLista(listaPokemon);
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
