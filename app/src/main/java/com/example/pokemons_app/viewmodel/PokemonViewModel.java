package com.example.pokemons_app.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokemons_app.model.Pokemon;
import com.example.pokemons_app.model.PokemonsResponse;
import com.example.pokemons_app.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {

    MutableLiveData<ArrayList<Pokemon>> pokemonMutableLiveData = new MutableLiveData<>();
    MutableLiveData<ArrayList<Pokemon>> FavPokemonslist = new MutableLiveData<>();

    Repository repository;

    public MutableLiveData<ArrayList<Pokemon>> getFavPokemonslist() {
        return FavPokemonslist;
    }

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonList() {
        return pokemonMutableLiveData;
    }

    @SuppressLint("CheckResult")
    public void getPokemons() {
        repository.getPokemons()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonsResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonsResponse pokemonResponse) throws Throwable {
                        ArrayList<Pokemon> list = pokemonResponse.getResults();
                        for (Pokemon pokemon : list) {
                            String url = pokemon.getUrl();
                            String[] pokemonIndex = url.split("/");
                            pokemon.setUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/" + pokemonIndex[pokemonIndex.length -1] + ".png");
                        }
                        return list;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonMutableLiveData.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }

    public void insertPokemon(Pokemon pokemon){repository.insertPokemon(pokemon);}
    public void deletPokemon (String pokemonname){repository.deletPokemon(pokemonname);}
    public LiveData<List<Pokemon>>getFavPokemon(){return repository.getFavPokemons();}
}

