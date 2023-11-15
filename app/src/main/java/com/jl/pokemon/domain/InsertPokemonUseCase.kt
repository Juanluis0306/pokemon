package com.jl.pokemon.domain

import com.jl.pokemon.database.PokemonRepository
import com.jl.pokemon.database.entities.PokemonEntity
import javax.inject.Inject

class InsertPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(listPokemon: List<PokemonEntity>) {
        return repository.insertData(listPokemon)
    }
}