package com.jl.pokemon.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jl.pokemon.R
import com.jl.pokemon.database.entities.PokemonEntity
import com.squareup.picasso.Picasso

class PokemonAdapter(private val listPokemon: List<PokemonEntity>, select: OnItemClickListener) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: PokemonEntity)
    }

    private val listener: OnItemClickListener = select

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listPokemon[position]
        val components = item.imageUrl.split("/")
        val number = components[components.size -1].replace(".png", "")
        holder.txtId.text = "#$number"
        holder.txtName.text = item.name

        Picasso.get()
            .load(item.imageUrl)
            .placeholder(R.drawable.pokemon_logo)
            .error(R.drawable.pokemon_logo)
            .into(holder.imgPokemon)

        holder.constraint.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtId: TextView
        var txtName: TextView
        var imgPokemon: ImageView
        var constraint: ConstraintLayout

        init {
            txtId = view.findViewById(R.id.txtId)
            txtName = view.findViewById(R.id.txtName)
            imgPokemon = view.findViewById(R.id.imgPokemon)
            constraint = view.findViewById(R.id.constraint)
        }
    }
}