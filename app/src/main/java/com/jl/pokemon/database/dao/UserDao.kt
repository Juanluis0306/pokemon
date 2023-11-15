package com.jl.pokemon.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jl.pokemon.database.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(list: List<UserEntity>?)

    @Query("SELECT * FROM user_table WHERE email LIKE :email AND password LIKE :password")
    suspend fun login(email: String?, password: String?): UserEntity?

    @Query("SELECT COUNT(email) FROM user_table")
    suspend fun totalRecords(): Int

}