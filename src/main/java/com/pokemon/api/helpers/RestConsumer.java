package com.pokemon.api.helpers;

import com.pokemon.api.entity.Pokemon;
import com.pokemon.api.service.impl.PokemonServiceImpl;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;


public class RestConsumer {

    public static void fetchPokemons(PokemonServiceImpl pokemonService)
    {

        pokemonService.clearDB();

        int PokemonNumberEveryRequest = 20;
        String url = "https://pokeapi.co/api/v2/pokemon/";
        RestTemplate restTemplate = new RestTemplate();
        int aa = 0;
        while(!url.equals("null")) {
            String respond;
            JSONObject jsonRespond = new JSONObject();
            JSONArray resultList = new JSONArray();
            //check whether  PokeAPI returns valid respond
            try {
                respond = restTemplate.getForObject(url, String.class);
                jsonRespond = new JSONObject(respond);
                url = jsonRespond.getString("next");
            } catch (JSONException e) {
                url = "null";
                break;
            }

            //get the pokemonList
            try {
                resultList = jsonRespond.getJSONArray("results");
            } catch (JSONException e) {
            }


            //find pokemons in Json Array
            int pokemonNumber = resultList.length();
            for (int pokemonIndex = 0; pokemonIndex < pokemonNumber; pokemonIndex++) {
                String pokemonName = "";
                String pokemonUrl = "";
                String imageUrl = "";
                ArrayList<String> abilities = new ArrayList<>();

                try {
                    JSONObject pokemon = resultList.getJSONObject(pokemonIndex);
                    pokemonName = pokemon.getString("name");
                    pokemonUrl = pokemon.getString("url");
                } catch (JSONException e) {

                }

                //fetch special pokemon data
                String pokemonRespond = restTemplate.getForObject(pokemonUrl, String.class);
                JSONObject jsonPokemonRespond = new JSONObject();
                JSONArray resultAbilityList = new JSONArray();
                try {
                    // get image
                    jsonPokemonRespond = new JSONObject(pokemonRespond);
                    JSONObject path = jsonPokemonRespond.getJSONObject("sprites");
                    path = path.getJSONObject("other");
                    path = path.getJSONObject("official-artwork");
                    imageUrl = path.getString("front_default");

                    resultAbilityList = jsonPokemonRespond.getJSONArray("abilities");
                    for (int abilityIndex = 0; abilityIndex < resultAbilityList.length(); abilityIndex++) {
                        JSONObject abilityJson = resultAbilityList.getJSONObject(abilityIndex);
                        path = abilityJson.getJSONObject("ability");
                        String abilityName = path.getString("name");
                        abilities.add(abilityName);
                    }
                } catch (JSONException e) {

                }

                Pokemon pokemon = new Pokemon(pokemonName, pokemonUrl, imageUrl, abilities);

                pokemonService.addPokemon(pokemon);

            }



        }


        



    }
}
