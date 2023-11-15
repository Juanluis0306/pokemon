package com.jl.pokemon.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jl.pokemon.database.dao.PokemonDao
import com.jl.pokemon.database.dao.UserDao
import com.jl.pokemon.database.entities.PokemonEntity
import com.jl.pokemon.database.entities.UserEntity

@Database(entities = [UserEntity::class, PokemonEntity::class], version = 2)
abstract class AppDataBase: RoomDatabase() {
    abstract fun getBlogDao(): UserDao
    abstract fun getPokemonDao(): PokemonDao
}