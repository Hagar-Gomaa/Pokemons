package com.example.pokemons_app.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemons_app.model.Pokemon;

@Database(entities = Pokemon.class ,version = 1,exportSchema = false)
abstract public class RoomDb extends RoomDatabase {

    abstract public Dao pokemonDao();
}
