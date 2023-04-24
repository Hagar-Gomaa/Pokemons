package com.example.pokemons_app.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemons_app.R;
import com.example.pokemons_app.model.Pokemon;
import com.example.pokemons_app.viewmodel.PokemonViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView favrecyclerView;

    PokemonRecycleAdapter pokemonAdapter;
    PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        pokemonAdapter = new PokemonRecycleAdapter(this);
        recyclerView.setAdapter(pokemonAdapter);
        swiptsetup();

        Button nextbutton = findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,FavPokemonsActivity2.class));
            }
        });

        viewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        viewModel.getPokemons();
        viewModel.getPokemonList().observe(this, new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                pokemonAdapter.setPokemonArrayList(pokemons);
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
                Toast.makeText(MainActivity.this, "Pokemon have added Successfully !", Toast.LENGTH_SHORT).show();

            }
        };
     ItemTouchHelper itemTouchHelper =new ItemTouchHelper(callback);
     itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}