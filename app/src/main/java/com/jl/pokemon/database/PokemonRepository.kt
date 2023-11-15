package com.jl.pokemon.database

import com.jl.pokemon.database.dao.PokemonDao
import com.jl.pokemon.database.entities.PokemonEntity
import com.jl.pokemon.domain.model.AboutPokemon
import com.jl.pokemon.domain.model.DetailPokemon
import com.jl.pokemon.domain.model.PokemonList
import com.jl.pokemon.network.PokemonService
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val api: PokemonService
) {

    suspend fun getAllPokemonsApi(): PokemonList? {
        return api.getPokemons()
    }

    suspend fun getDetailPokemonApi(pokemonId: String): DetailPokemon? {
        return api.getDetailPokemonApi(pokemonId)
    }

    suspend fun getAboutPokemon(pokemonId: String): AboutPokemon? {
        return api.getAboutPokemon(pokemonId)
    }

    suspend fun insertData(listUserEntity: List<PokemonEntity>?) {
        pokemonDao.updateData(listUserEntity)
    }

    suspend fun totalRecords(): Int {
        return pokemonDao.totalRecords()
    }

    suspend fun getAllPokemons(): List<PokemonEntity> {
        return pokemonDao.getAllPokemon()
    }
}