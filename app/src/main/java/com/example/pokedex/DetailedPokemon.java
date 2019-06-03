package com.example.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailedPokemon extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        TextView pokeName = findViewById(R.id.name_detailed);
        pokeName.setText(getIntent().getStringExtra("Name"));

        ImageView pokeImg = findViewById(R.id.img_detailed);
        /*Glide.with(DetailedPokemon.this)
                .load(getIntent().getStringExtra("Img"))
                .into(pokeImg);
*/
        TextView pokeExp = findViewById(R.id.exp_detailed);
        pokeExp.setText(getIntent().getStringExtra("Exp"));

        TextView pokeHeight = findViewById(R.id.height_detailed);
        pokeHeight.setText(getIntent().getStringExtra("Height"));

        TextView pokeWeight = findViewById(R.id.weight_detailed);
        pokeWeight.setText(getIntent().getStringExtra("Weight"));
    }
}
