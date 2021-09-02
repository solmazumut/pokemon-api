package com.pokemon.api.controller;

import com.pokemon.api.service.impl.PokemonServiceImpl;
import com.pokemon.api.helpers.RestConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/pokemons")
public class PokemonController {
    @Autowired
    private PokemonServiceImpl service;


    @PutMapping(path = "/fetch-pokemon-data")
    public ResponseEntity fetchPokemonData() {
        RestConsumer.fetchPokemons(service);
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.ACCEPTED);
        return responseEntity;
    }

    @GetMapping(path = "/all-pokemons")
    public ResponseEntity<String> getPokemons() {
        String pokemons = service.getAllPokemons();
        ResponseEntity responseEntity = new ResponseEntity(pokemons, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(path = "/all-pokemons-with-abilities")
    public ResponseEntity<String> getPokemonsWithAbilities() {
        String pokemons = service.getAllPokemonsWithAbilities();
        ResponseEntity responseEntity = new ResponseEntity(pokemons, HttpStatus.OK);
        return responseEntity;
    }




}
