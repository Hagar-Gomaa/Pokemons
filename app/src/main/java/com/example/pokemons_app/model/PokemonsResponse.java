package com.example.pokemons_app.model;

import java.util.ArrayList;

public class PokemonsResponse {
    private String previous, next;
    private int count;
    private ArrayList<Pokemon> results;

    public PokemonsResponse(String previous, String next, int count, ArrayList<Pokemon> results) {
        this.previous = previous;
        this.next = next;
        this.count = count;
        this.results = results;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
