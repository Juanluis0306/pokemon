package com.jl.pokemon.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
class PokemonEntity(
    @PrimaryKey()
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "imageUrl")
    var imageUrl: String
)