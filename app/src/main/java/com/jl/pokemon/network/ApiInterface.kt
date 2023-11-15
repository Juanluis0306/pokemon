package com.jl.pokemon.network

import com.jl.pokemon.domain.model.AboutPokemon
import com.jl.pokemon.domain.model.DetailPokemon
import com.jl.pokemon.domain.model.PokemonList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("pokemon?limit=151")
    suspend fun getPokemon(): Response<PokemonList>

    @GET("pokemon/{pokemon_id}")
    suspend fun getDetailPokemon(
        @Path("pokemon_id") pokemonId: String
    ): Response<DetailPokemon>

    @GET("pokemon-species/{pokemon_id}")
    suspend fun getAboutPokemon(
        @Path("pokemon_id") pokemonId: String
    ): Response<AboutPokemon>
}