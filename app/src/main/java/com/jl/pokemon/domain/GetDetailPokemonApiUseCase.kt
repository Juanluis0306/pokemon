package com.jl.pokemon.domain

import com.jl.pokemon.database.PokemonRepository
import com.jl.pokemon.domain.model.DetailPokemon
import javax.inject.Inject

class GetDetailPokemonApiUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(pokemonId: String): DetailPokemon? {
        return repository.getDetailPokemonApi(pokemonId)
    }
}