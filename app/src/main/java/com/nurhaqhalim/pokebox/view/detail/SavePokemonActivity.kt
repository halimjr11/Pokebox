package com.nurhaqhalim.pokebox.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.pokebox.databinding.ActivitySavePokemonBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SavePokemonActivity : AppCompatActivity() {
    private lateinit var activitySavePokemonBinding: ActivitySavePokemonBinding
    private val viewModel: DetailViewModel by viewModel()

    companion object{
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySavePokemonBinding = ActivitySavePokemonBinding.inflate(layoutInflater)
        setContentView(activitySavePokemonBinding.root)

        val data = intent.getParcelableExtra<Pokemon>(DetailActivity.EXTRA_DATA) as Pokemon
        setView(data)

        val nickname = activitySavePokemonBinding.etNick.text.toString().trim()

        activitySavePokemonBinding.saveButton.setOnClickListener {
            viewModel.saveAndSetNickname(data.id, nickname)
            val intent = Intent(this@SavePokemonActivity, DetailActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra(DetailActivity.EXTRA_DATA, data)
            startActivity(intent)
        }
    }

    fun setView(pokemon: Pokemon){
        activitySavePokemonBinding.savePokeName.text = pokemon.name
        Glide.with(activitySavePokemonBinding.root).load(pokemon.image).into(activitySavePokemonBinding.saveImage)
    }
}