package com.pokemon.api.service;

import com.pokemon.api.entity.Pokemon;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

public interface PokemonService {
    public void clearDB();

    public String getAllPokemons();

    public String getAllPokemonsWithAbilities();
}
