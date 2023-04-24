package com.example.pokemons_app.db;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.pokemons_app.model.Pokemon;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    public void insertPokemon (Pokemon pokemon);
    @Query("delete from fav_pokemons where name =:pokemonname ")
    public void deletpokemon (String pokemonname);
    @Query("select * from fav_pokemons")
    public LiveData<List<Pokemon>> getfavpokemons();
}
