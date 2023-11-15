package com.jl.pokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jl.pokemon.database.entities.UserEntity
import com.jl.pokemon.domain.CountUserUseCase
import com.jl.pokemon.domain.InsertUserUseCase
import com.jl.pokemon.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val insertUserUseCase: InsertUserUseCase,
    private val countUserUseCase: CountUserUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    val countUser = MutableLiveData<Int>()
    val userEntity = MutableLiveData<UserEntity>()

    fun insertUsersDefault(listUser: List<UserEntity>) {
        viewModelScope.launch {
            insertUserUseCase(listUser)
        }
    }

    fun countUser() {
        viewModelScope.launch {
            countUser.postValue(countUserUseCase())
        }
    }

    fun login(emailUser: String, passwordUser: String) {
        viewModelScope.launch {
            userEntity.postValue(loginUseCase(email = emailUser ?: "", password = passwordUser ?: ""))
        }
    }
}