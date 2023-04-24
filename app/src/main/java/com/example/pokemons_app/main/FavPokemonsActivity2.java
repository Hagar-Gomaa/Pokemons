package com.example.pokemons_app.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pokemons_app.R;
import com.example.pokemons_app.model.Pokemon;
import com.example.pokemons_app.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavPokemonsActivity2 extends AppCompatActivity {
    RecyclerView favrecyclerView;

    PokemonRecycleAdapter pokemonAdapter;
    PokemonViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_pokemons2);

        favrecyclerView = findViewById(R.id.recyclerView);
        pokemonAdapter = new PokemonRecycleAdapter(this);
        favrecyclerView.setAdapter(pokemonAdapter);
        swiptsetup();

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getFavPokemon();
        viewModel.getFavPokemonslist().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                pokemonAdapter.setPokemonArrayList((ArrayList<Pokemon>) pokemons);
            }
        });
        Button backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavPokemonsActivity2.this, MainActivity.class));
            }
        });
    }
        public void swiptsetup(){
            ItemTouchHelper.SimpleCallback callback =new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position_pokemonAdapter = viewHolder.getAdapterPosition();
                    Pokemon swipedPokemon = pokemonAdapter.getPokemon(position_pokemonAdapter);
                    viewModel.insertPokemon(swipedPokemon);
                    Toast.makeText(FavPokemonsActivity2.this, "Pokemon have added Successfully !", Toast.LENGTH_SHORT).show();

                }
            };
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
            itemTouchHelper.attachToRecyclerView(favrecyclerView);

        }


    }
