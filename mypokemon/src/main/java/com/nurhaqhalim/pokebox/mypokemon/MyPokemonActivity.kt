package com.nurhaqhalim.pokebox.mypokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.pokebox.mypokemon.databinding.ActivityMyPokemonBinding
import com.nurhaqhalim.pokebox.mypokemon.di.myPokemonModule
import com.nurhaqhalim.pokebox.view.adapter.PokemonAdapter
import com.nurhaqhalim.pokebox.view.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.context.loadKoinModules

@ExperimentalCoroutinesApi
class MyPokemonActivity : AppCompatActivity() {
    private lateinit var myPokemonBinding: ActivityMyPokemonBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private val viewModel: MyPokemonViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myPokemonBinding = ActivityMyPokemonBinding.inflate(layoutInflater)
        setContentView(myPokemonBinding.root)

        loadKoinModules(myPokemonModule)

        showLoading(true)
        pokemonAdapter = PokemonAdapter()

        myPokemonBinding.savedList.apply {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            this.adapter = pokemonAdapter
        }

        viewModel.getSavedList().observe(this) {
            showLoading(false)
            if (it.isNotEmpty()) {
                pokemonAdapter.setData(it)
            } else {
                myPokemonBinding.savedList.visibility = View.GONE
                myPokemonBinding.notFound.visibility = View.VISIBLE
            }
        }

        pokemonAdapter.setOnItemClickCallback(object: PokemonAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Pokemon) {
                Intent(this@MyPokemonActivity, DetailActivity::class.java).also{
                    it.putExtra(DetailActivity.EXTRA_DATA,data)
                    startActivity(it)
                }
            }
        })

        supportActionBar?.title= "My Pokemon List"

    }

    private fun showLoading(state: Boolean){
        if (state){
            myPokemonBinding.savedProgress.visibility = View.VISIBLE
        }else{
            myPokemonBinding.savedProgress.visibility = View.GONE
        }
    }
}