package com.jl.pokemon.domain

import com.jl.pokemon.database.PokemonRepository
import com.jl.pokemon.domain.model.AboutPokemon
import javax.inject.Inject

class GetAboutPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(pokemonId: String): AboutPokemon? {
        return repository.getAboutPokemon(pokemonId)
    }
}