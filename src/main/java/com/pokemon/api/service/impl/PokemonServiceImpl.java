package com.pokemon.api.service.impl;

import com.pokemon.api.entity.Pokemon;
import com.pokemon.api.repository.PokemonRepository;
import com.pokemon.api.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    private final PokemonRepository pokemonRepository;

    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }


    public Pokemon addPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void clearDB() {
        pokemonRepository.deleteAll();
    }

    public String getAllPokemons() {
        List<Pokemon> pokemonList = pokemonRepository.findAll();

        JSONArray pokemons = new JSONArray();
        for (Pokemon pokemon : pokemonList) {
            JSONObject pokemonJson = new JSONObject();
            try {
                pokemonJson.put("name", pokemon.getName());
                pokemonJson.put("imageUrl", pokemon.getImageUrl());
                pokemons.put(pokemonJson);
            } catch (JSONException e) {}

        }
        return pokemons.toString();
    }

    public String getAllPokemonsWithAbilities() {
        List<Pokemon> pokemonList = pokemonRepository.findAll();

        JSONArray pokemons = new JSONArray();
        for (Pokemon pokemon : pokemonList) {
            JSONObject pokemonJson = new JSONObject();
            try {
                pokemonJson.put("name", pokemon.getName());
                pokemonJson.put("imageUrl", pokemon.getImageUrl());
                JSONArray abilities = new JSONArray();
                for (String abilityName : pokemon.getAbilities()) {
                    abilities.put(abilityName);
                }
                pokemonJson.put("abilities", abilities);
                pokemons.put(pokemonJson);
            } catch (JSONException e) {}

        }
        return pokemons.toString();
    }
}
