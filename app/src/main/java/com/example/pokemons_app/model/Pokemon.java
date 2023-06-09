package com.example.pokemons_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_pokemons")
public class Pokemon {
    @PrimaryKey(autoGenerate = true)
    int id ;
    String name, url;

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
