package com.jl.pokemon.database

import com.jl.pokemon.database.dao.UserDao
import com.jl.pokemon.database.entities.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao) {

    suspend fun insertData(listUserEntity: List<UserEntity>?) {
        userDao.updateData(listUserEntity)
    }

    suspend fun login(email: String, password: String): UserEntity?{
        return userDao.login(email = email, password = password)
    }

    suspend fun totalRecords(): Int{
        return userDao.totalRecords()
    }
}