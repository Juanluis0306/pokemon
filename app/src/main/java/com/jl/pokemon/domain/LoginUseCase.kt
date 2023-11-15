package com.jl.pokemon.domain

import com.jl.pokemon.database.UserRepository
import com.jl.pokemon.database.entities.UserEntity
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(email: String, password: String): UserEntity? {
        return repository.login(email = email, password = password)
    }
}