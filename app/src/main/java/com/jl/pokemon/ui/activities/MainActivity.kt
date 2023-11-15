package com.jl.pokemon.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jl.pokemon.R
import com.jl.pokemon.Utils.NetworkAvailable
import com.jl.pokemon.adapter.PokemonAdapter
import com.jl.pokemon.database.entities.PokemonEntity
import com.jl.pokemon.databinding.ActivityMainBinding
import com.jl.pokemon.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PokemonAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private var prefs: SharedPreferences? = null
    private val viewModel: MainViewModel by viewModels()
    private lateinit var pokemonAdapter: PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observers()
        listeners()
    }

    private fun observers() {
        viewModel.countPokemon.observe(this) {
            if (it == 0) {
                if (NetworkAvailable().isNetworkAvailable(this))
                    viewModel.getAllPokemonApi()
                else
                    showErrorRed()
            } else {
                viewModel.getAllPokemon()
            }
        }

        viewModel.listPokemonApi.observe(this) {
            val listPokemonEntity: ArrayList<PokemonEntity> = arrayListOf()
            it.results.forEach { x ->
                listPokemonEntity.add(PokemonEntity(x.id, x.name, x.imageUrl))
            }
            viewModel.insertPokemon(listPokemonEntity)
            createRv(listPokemonEntity)
        }

        viewModel.listPokemon.observe(this) {
            createRv(it)
        }
        viewModel.isLoading.observe(this) {
            binding.loading.isVisible = it
        }
    }

    private fun listeners() {
        viewModel.countPokemon()
        prefs = getSharedPreferences(getString(R.string.save_session_data), MODE_PRIVATE)
        binding.btnClose.setOnClickListener {
            showAlertSession()
        }
    }

    private fun showAlertSession() {
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText(getString(R.string.alert_close))
            .setCancelText(getString(R.string.cancel))
            .setConfirmText(getString(R.string.accept))
            .showCancelButton(true)
            .setCancelClickListener { sDialog -> sDialog.cancel() }
            .setConfirmClickListener { closeSession() }
            .show()
    }

    private fun closeSession() {
        prefs?.edit()?.clear()?.apply()
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun createRv(list: List<PokemonEntity>) {
        binding.rvPokemon.layoutManager = GridLayoutManager(applicationContext, 3)
        pokemonAdapter = PokemonAdapter(list, this)
        binding.rvPokemon.adapter = pokemonAdapter
    }

    override fun onItemClick(item: PokemonEntity) {
        if (NetworkAvailable().isNetworkAvailable(this)) {
            val i = Intent(this, DetailPokemonActivity::class.java)
            val components = item.imageUrl.split("/")
            val number = components[components.size - 1].replace(".png", "")
            i.putExtra("POKEMON_ID", number)
            startActivity(i)
        } else {
            showErrorRed()
        }
    }

    private fun showErrorRed() {
        SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
            .setTitleText("Oops...")
            .setContentText("No internet connection")
            .show()
    }
}