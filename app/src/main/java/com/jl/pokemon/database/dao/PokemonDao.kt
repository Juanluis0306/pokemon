package com.jl.pokemon.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jl.pokemon.database.entities.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(list: List<PokemonEntity>?)

    @Query("SELECT COUNT(name) FROM pokemon_table")
    suspend fun totalRecords(): Int

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllPokemon(): List<PokemonEntity>
}