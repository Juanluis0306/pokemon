package com.jl.pokemon.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jl.pokemon.domain.GetAboutPokemonUseCase
import com.jl.pokemon.domain.GetDetailPokemonApiUseCase
import com.jl.pokemon.domain.model.AboutPokemon
import com.jl.pokemon.domain.model.DetailPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel @Inject constructor(
    private val getDetailPokemonApiUseCase: GetDetailPokemonApiUseCase,
    private val getAboutPokemonUseCase: GetAboutPokemonUseCase
) :
    ViewModel() {

    val pokemonDetail = MutableLiveData<DetailPokemon>()
    val pokemonAbout = MutableLiveData<AboutPokemon>()
    val isLoading = MutableLiveData<Boolean>()

    fun getDetailPokemon(pokemonId: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getDetailPokemonApiUseCase(pokemonId)
            if (result != null) {
                isLoading.postValue(false)
                pokemonDetail.postValue(result)
            }
        }
    }

    fun getAboutPokemon(pokemonId: String) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getAboutPokemonUseCase(pokemonId)
            if (result != null) {
                isLoading.postValue(false)
                pokemonAbout.postValue(result)
            }
        }
    }
}