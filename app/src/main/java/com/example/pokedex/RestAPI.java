package com.example.pokedex;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestAPI {
    @GET("pokemon")
    Call<PokemonFeed> getData();

    //Call<List<Pokemon>> getData();
    //Modificar Pokemon, Adapter
}
