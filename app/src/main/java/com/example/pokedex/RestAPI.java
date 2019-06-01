package com.example.pokedex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestAPI {
    @GET("{id}")
    Call<Pokemon> getData(@Path("id") int idPokemon);

    //Call<List<Pokemon>> getData();
    //Modificar Pokemon, Adapter
}
