package com.jl.pokemon.domain

import com.jl.pokemon.database.UserRepository
import com.jl.pokemon.database.entities.UserEntity
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(listUser: List<UserEntity>) {
        return repository.insertData(listUser)
    }
}