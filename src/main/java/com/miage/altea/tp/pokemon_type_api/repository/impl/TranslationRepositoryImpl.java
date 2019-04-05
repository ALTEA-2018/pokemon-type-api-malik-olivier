package com.miage.altea.tp.pokemon_type_api.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.Translation;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {
    private Map<Locale, List<Translation>> translations;

    private List<Translation> defaultTranslations;

    public TranslationRepositoryImpl() {
        try {
            var objectMapper = new ObjectMapper();

            var frenchTranslationStream = new ClassPathResource("translations-fr.json").getInputStream();
            var frenchTranslationsArray = objectMapper.readValue(frenchTranslationStream, Translation[].class);

            var englishTranslationStream = new ClassPathResource("translations-en.json").getInputStream();
            var englishTranslationsArray = objectMapper.readValue(englishTranslationStream, Translation[].class);

            this.translations = Map.of(
                    Locale.FRENCH, List.of(frenchTranslationsArray),
                    Locale.ENGLISH, List.of(englishTranslationsArray),
                    Locale.US, List.of(englishTranslationsArray),
                    Locale.FRANCE,List.of(frenchTranslationsArray)
            );

            this.defaultTranslations = List.of(englishTranslationsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPokemonName(int id, Locale locale) {
        var pokemons = translations.get(locale);
        return pokemons.stream().filter(translation -> translation.getId() == id).findFirst().get().getName();
    }

    public Map<Locale, List<Translation>> getTranslations() {
        return translations;
    }

    public void setTranslations(Map<Locale, List<Translation>> translations) {
        this.translations = translations;
    }

    public List<Translation> getDefaultTranslations() {
        return defaultTranslations;
    }

    public void setDefaultTranslations(List<Translation> defaultTranslations) {
        this.defaultTranslations = defaultTranslations;
    }
}
