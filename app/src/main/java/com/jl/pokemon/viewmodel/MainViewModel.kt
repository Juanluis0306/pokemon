package com.jl.pokemon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jl.pokemon.database.entities.PokemonEntity
import com.jl.pokemon.database.entities.UserEntity
import com.jl.pokemon.domain.CountPokemonUseCase
import com.jl.pokemon.domain.GetPokemonApiUseCase
import com.jl.pokemon.domain.GetPokemonUseCase
import com.jl.pokemon.domain.InsertPokemonUseCase
import com.jl.pokemon.domain.model.PokemonList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertPokemonUseCase: InsertPokemonUseCase,
    private val getAllPokemonUseCase: GetPokemonUseCase,
    private val countPokemonUseCase: CountPokemonUseCase,
    private val getAllPokemonApiUseCase: GetPokemonApiUseCase,
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val countPokemon = MutableLiveData<Int>()
    val listPokemon = MutableLiveData<List<PokemonEntity>>()
    val listPokemonApi = MutableLiveData<PokemonList>()

    fun countPokemon() {
        viewModelScope.launch {
            countPokemon.postValue(countPokemonUseCase())
        }
    }

    fun insertPokemon(listPokemon: List<PokemonEntity>) {
        viewModelScope.launch {
            insertPokemonUseCase(listPokemon)
        }
    }

    fun getAllPokemon() {
        viewModelScope.launch {
            listPokemon.postValue(getAllPokemonUseCase())
        }
    }

    fun getAllPokemonApi() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getAllPokemonApiUseCase()
            if (result != null) {
                listPokemonApi.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}