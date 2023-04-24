package com.example.pokemons_app.domain;

import com.example.pokemons_app.model.PokemonsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("pokemon")
    Observable<PokemonsResponse> getPokemons();
}
