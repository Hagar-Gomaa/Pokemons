package com.example.pokemons_app.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pokemons_app.R;
import com.example.pokemons_app.model.Pokemon;

import java.util.ArrayList;

public class PokemonRecycleAdapter extends RecyclerView.Adapter<PokemonRecycleAdapter.PokemonViewHolder> {

    ArrayList<Pokemon> pokemonArrayList;

    public PokemonRecycleAdapter(Context context) {
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokemonitem, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.pokemonName.setText(pokemonArrayList.get(position).getName());
        Glide.with(context).load(pokemonArrayList.get(position).getUrl())
                .into(holder.pokemonImge);

    }
    public Pokemon getPokemon(int position){
        return pokemonArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return pokemonArrayList.size();
    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView pokemonImge;
        TextView pokemonName;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonImge = itemView.findViewById(R.id.pokemonImage);
            pokemonName = itemView.findViewById(R.id.pokemoneName);
        }
    }

    {

    }

    public void setPokemonArrayList(ArrayList<Pokemon> pokemonArrayList) {
        this.pokemonArrayList = pokemonArrayList;
    }
}
