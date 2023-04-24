package com.example.pokemons_app.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemons_app.db.Dao;
import com.example.pokemons_app.db.RoomDb;
import com.example.pokemons_app.domain.ApiService;
import com.example.pokemons_app.model.Pokemon;
import com.example.pokemons_app.model.PokemonsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    ApiService apiService;
    Dao dao;
    @Inject
    public Repository(ApiService apiService, Dao dao) {
        this.apiService = apiService;
        this.dao = dao;
    }




    public Observable<PokemonsResponse> getPokemons() {
        return apiService.getPokemons();
    }

    public void insertPokemon (Pokemon pokemon){dao.insertPokemon(pokemon) ;}
    public void deletPokemon (String pokemonname){dao.deletpokemon(pokemonname);}
    public LiveData<List<Pokemon>>getFavPokemons(){return dao.getfavpokemons();}

}
