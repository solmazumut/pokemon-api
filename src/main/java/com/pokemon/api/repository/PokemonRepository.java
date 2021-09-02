package com.pokemon.api.repository;

import com.pokemon.api.entity.Pokemon;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CouchbaseRepository<Pokemon, String> {
    @Override
    void deleteById(String id);

    @Override
    void deleteAll();

    void findByName(String name);
}
