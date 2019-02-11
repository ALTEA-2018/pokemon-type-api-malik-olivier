package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/pokemon-types")

public class PokemonTypeController {
    private PokemonTypeService service;

    public PokemonTypeController(PokemonTypeService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public PokemonType getPokemonTypeFromId(@PathVariable("id") int id){
        return service.getPokemonType(id);
    }

    @GetMapping(value = "/",params = "name")
    public PokemonType getPokemonTypeFromName(@RequestParam("name") String name){
        return service.getPokemonType(name);
    }

    @GetMapping(value = "/",params = "types")
    public List<PokemonType> getPokemonTypeFromName(@RequestParam("types") List<String> types){
        return service.getPokemonTypesByType(types);
    }

    @GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
        return service.getAllPokemonTypes();
    }
}
