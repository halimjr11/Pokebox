package com.nurhaqhalim.pokebox.view.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.nurhaqhalim.pokebox.R
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.pokebox.databinding.ActivityMainBinding
import com.nurhaqhalim.pokebox.view.adapter.PokemonAdapter
import com.nurhaqhalim.pokebox.view.detail.DetailActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var pokemonAdapter: PokemonAdapter
    private val mainViewModel: MainViewModel by viewModel()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        showLoading(true)
        pokemonAdapter = PokemonAdapter()
        pokemonAdapter.notifyDataSetChanged()

        activityMainBinding.pokeList.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            setHasFixedSize(true)
            adapter = pokemonAdapter
        }

        pokemonAdapter.setOnItemClickCallback(object : PokemonAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Pokemon) {
                showSelected(data)
            }
        })

        mainViewModel.getAllData().observe(this) {
            showLoading(false)
            print("cok ini lagi ngeload")
            if (it.isEmpty()) {
                activityMainBinding.pokeList.visibility = View.GONE
                activityMainBinding.mainProgress.visibility = View.VISIBLE
            } else {
                pokemonAdapter.setData(it)
            }
        }
    }

    private fun showSelected(data: Pokemon){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DATA, data)
        startActivity(intent)
    }

    private fun showLoading(state: Boolean){
        if (state){
            activityMainBinding.mainProgress.visibility = View.VISIBLE
        }else{
            activityMainBinding.mainProgress.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.my_pokemon_menu -> {
                val uri = Uri.parse("pokebox://mypokemon")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }


}