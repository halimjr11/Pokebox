package com.nurhaqhalim.pokebox.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.pokebox.databinding.PokeListBinding

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private val result = ArrayList<Pokemon>()

    private lateinit var onItemClickCallback: OnItemClickCallback

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<Pokemon>) {
        result.clear()
        result.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(val binding: PokeListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        PokeListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = result[position]
        val imageSource = pokemon.image
        val height = "${pokemon.height * 10} cm"
        val weight = "${pokemon.weight / 10} kg"

        holder.binding.pokeName.text = pokemon.name
        holder.binding.pokeHeight.text = height
        holder.binding.pokeWeight.text = weight

        Glide.with(holder.binding.root)
            .load(imageSource)
            .into(holder.binding.pokeImage)

        holder.binding.root.setOnClickListener{
            onItemClickCallback.onItemClicked(result[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount(): Int = result.size

    interface OnItemClickCallback{
        fun onItemClicked(data: Pokemon)
    }

}