package com.jl.pokemon.domain

import com.jl.pokemon.database.UserRepository
import javax.inject.Inject

class CountUserUseCase @Inject constructor(private val repository: UserRepository){

    suspend operator fun invoke(): Int {
        return repository.totalRecords()
    }
}