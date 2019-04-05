package com.miage.altea.tp.pokemon_type_api.service.impl;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private PokemonTypeRepository pokemonTypeRepository;
    private TranslationRepository translationRepository;

    public PokemonTypeServiceImpl(PokemonTypeRepository pokemonTypeRepository){
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    public PokemonTypeServiceImpl() {
    }

    @Override
    public PokemonType getPokemonType(int id) {
        var pokemon = pokemonTypeRepository.findPokemonTypeById(id);
        pokemon.setName(translationRepository.getPokemonName(id,LocaleContextHolder.getLocale()));
        return pokemon;
    }

    @Override
    public PokemonType getPokemonType(String name) {
        var pokemon = pokemonTypeRepository.findPokemonTypeByName(name);
        pokemon.setName(translationRepository.getPokemonName(pokemon.getId(),LocaleContextHolder.getLocale()));
        return pokemon;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(){
        var pokemons = pokemonTypeRepository.findAllPokemonType();
        pokemons.stream()
                .forEach(pokemon -> pokemon.setName(this.translationRepository.getPokemonName(pokemon.getId(),LocaleContextHolder.getLocale())));
        return  pokemons;
    }

    public PokemonTypeRepository getPokemonTypeRepository() {
        return pokemonTypeRepository;
    }

    @Autowired
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Override
    public List<PokemonType> getPokemonTypesByType(List<String> types) {
        return this.pokemonTypeRepository.findPokemonTypesByTypes(types);
    }

    public TranslationRepository getTranslationRepository() {
        return translationRepository;
    }

    @Autowired
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository = translationRepository;
    }
}
