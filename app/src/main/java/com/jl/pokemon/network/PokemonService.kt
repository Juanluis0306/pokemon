package com.jl.pokemon.network

import com.jl.pokemon.domain.model.AboutPokemon
import com.jl.pokemon.domain.model.DetailPokemon
import com.jl.pokemon.domain.model.PokemonList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val api: ApiInterface) {

    suspend fun getPokemons(): PokemonList? {
        return withContext(Dispatchers.IO) {
            val response = api.getPokemon()
            response.body()
        }
    }

    suspend fun getDetailPokemonApi(pokemonId: String): DetailPokemon? {
        return withContext(Dispatchers.IO) {
            val response = api.getDetailPokemon(pokemonId)
            response.body()
        }
    }

    suspend fun getAboutPokemon(pokemonId: String): AboutPokemon? {
        return withContext(Dispatchers.IO) {
            val response = api.getAboutPokemon(pokemonId)
            response.body()
        }
    }
}