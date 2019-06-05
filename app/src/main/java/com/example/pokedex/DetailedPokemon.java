package com.example.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailedPokemon extends AppCompatActivity {
    private Retrofit resultRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        final TextView pokeName = findViewById(R.id.name_detailed);
        final ImageView pokeImg = findViewById(R.id.img_detailed);
        final TextView pokeExp = findViewById(R.id.exp_detailed);
        final TextView pokeHeight = findViewById(R.id.height_detailed);
        final TextView pokeWeight = findViewById(R.id.weight_detailed);

        resultRetrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAPI resultAPI = resultRetrofit.create(RestAPI.class);

        Call<Pokemon> call = resultAPI.getData(getIntent().getIntExtra("position",1));

        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()){
                    Pokemon pokemon = response.body();
                    pokeName.setText(pokemon.getName());
                    pokeExp.setText(String.valueOf(pokemon.getBase_experience()));
                    pokeHeight.setText(String.valueOf(pokemon.getHeight()));
                    pokeWeight.setText(String.valueOf(pokemon.getWeight()));
                    Glide.with(DetailedPokemon.this)
                            .load(getIntent().getStringExtra("url"))
                            .into(pokeImg);

                }else {
                    Log.e("Error: ", "on Response " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("Error: ", "onFailure" + t.getMessage());
            }
        });

        /*
        pokeName.setText(getIntent().getStringExtra("Name"));

        Glide.with(DetailedPokemon.this)
                .load(getIntent().getStringExtra("Img"))
                .into(pokeImg);

        pokeExp.setText(String.valueOf(getIntent().getIntExtra("Exp",0)));

        pokeHeight.setText(String.valueOf(getIntent().getIntExtra("Height",0)));

        pokeWeight.setText(String.valueOf(getIntent().getIntExtra("Weight",0)));*/
    }
}
