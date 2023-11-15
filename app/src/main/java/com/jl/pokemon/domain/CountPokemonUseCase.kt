package com.jl.pokemon.domain

import com.jl.pokemon.database.PokemonRepository
import javax.inject.Inject

class CountPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(): Int {
        return repository.totalRecords()
    }
}