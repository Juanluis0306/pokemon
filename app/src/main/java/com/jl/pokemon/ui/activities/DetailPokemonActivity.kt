package com.jl.pokemon.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.jl.pokemon.R
import com.jl.pokemon.databinding.ActivityDetailPokemonBinding
import com.jl.pokemon.viewmodel.DetailPokemonViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPokemonBinding
    private val viewModel: DetailPokemonViewModel by viewModels()
    private var pokemonId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pokemonId = intent.getStringExtra("POKEMON_ID") ?: ""
        observers()
        listeners()
    }

    private fun observers() {
        viewModel.pokemonDetail.observe(this) {
            binding.pokemonName = it.name
            binding.pokemonPower = "HP ${it.experience}"
            binding.pokemonHeight = it.getHeightString()
            binding.pokemonWeight = it.getWeightString()
            Picasso.get()
                .load(it.getImageUrl())
                .placeholder(R.drawable.pokemon_logo)
                .error(R.drawable.pokemon_logo)
                .into(binding.imgPokemon)
            viewModel.getAboutPokemon(pokemonId)
        }

        viewModel.isLoading.observe(this) {
            binding.loading.isVisible = it
        }
        viewModel.pokemonAbout.observe(this) {
            it.flavor_text_entries.forEach { value ->
                if (value.language.name == "en") {
                    binding.pokemonDescription = value.flavor_text
                }
            }
        }
    }

    private fun listeners() {
        binding.executePendingBindings()
        viewModel.getDetailPokemon(pokemonId)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}