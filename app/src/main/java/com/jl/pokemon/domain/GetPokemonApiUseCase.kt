package com.jl.pokemon.domain

import com.jl.pokemon.database.PokemonRepository
import com.jl.pokemon.domain.model.PokemonList
import javax.inject.Inject

class GetPokemonApiUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): PokemonList? {
        return repository.getAllPokemonsApi()
    }
}