package com.example.pokedex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestAPI {
    @GET("pokemon/{id}")
    Call<Pokemon> getData(@Path("id") int idPokemon);

    //Call<List<Pokemon>> getData();
    //Modificar Pokemon, Adapter

    @GET("pokemon")
    Call<PokemonFeed> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
    @GET("pokemon")
    Call<PokemonFeed> obtenerListaPokemon();
}
