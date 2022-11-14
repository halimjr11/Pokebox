package com.nurhaqhalim.pokebox.view.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.nurhaqhalim.core.domain.model.Pokemon
import com.nurhaqhalim.pokebox.databinding.ActivityDetailBinding
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

@ExperimentalCoroutinesApi
class DetailActivity : AppCompatActivity() {
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityDetailBinding.root)

        showLoading(true)

        val data = intent.getParcelableExtra<Pokemon>(EXTRA_DATA) as Pokemon
        val name = data.name
        val id = data.id

        getDetail(name)

        var isSaved = false

        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkSave(id)
            Timber.tag("cek").d(count.toString())
            withContext(Dispatchers.Main){
                if (count > 0){
                    savedState()
                    isSaved = true
                }else{
                    unSavedState()
                    isSaved = false
                }
            }
        }

        activityDetailBinding.catchButton.setOnClickListener{
            val random = (0..100).random()
            if (random>50){
                Toast.makeText(this, "Kamu Berhasil Menangkap $name", Toast.LENGTH_LONG).show()
                val intent = Intent(this@DetailActivity, SavePokemonActivity::class.java)
                intent.putExtra(SavePokemonActivity.EXTRA_DATA, data)
                startActivity(intent)
            } else{
                Toast.makeText(this, "Gagal Menangkap $name", Toast.LENGTH_LONG).show()
            }
        }

        activityDetailBinding.unsaveButton.setOnClickListener {
            viewModel.unSave(id)
            unSavedState()
        }
    }

    private fun getDetail(name: String){
        showLoading(false)
        viewModel.getDetail(name).observe(this) {
            println(it.toString())
            if (it != null) {
                showResult(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(results: Pokemon) {
        val image = results.image
        activityDetailBinding.pokeName.text = results.name
        activityDetailBinding.pokeHp.text = results.hp.toString()
        activityDetailBinding.pokeAttack.text = results.attack.toString()
        activityDetailBinding.pokeDefense.text = results.defense.toString()
        activityDetailBinding.pokeSpAttack.text = results.specialAttack.toString()
        activityDetailBinding.pokeSpDefense.text = results.specialDefense.toString()
        activityDetailBinding.pokeSpeed.text = results.speed.toString()
        activityDetailBinding.detailAbility.text = results.ability
        activityDetailBinding.detailType.text = "Type: ${results.type}"

        activityDetailBinding.hpValue.progress = results.hp
        activityDetailBinding.attackValue.progress = results.attack
        activityDetailBinding.defenseValue.progress = results.defense
        activityDetailBinding.spAttackValue.progress = results.specialAttack
        activityDetailBinding.spDefenseValue.progress = results.specialDefense
        activityDetailBinding.speedValue.progress = results.speed

        Glide.with(activityDetailBinding.root).load(image).into(activityDetailBinding.detailImage)
    }

    private fun showLoading(state: Boolean){
        if (state){
            activityDetailBinding.progressbar1.visibility = View.VISIBLE
        }else{
            activityDetailBinding.progressbar1.visibility = View.GONE
        }
    }

    private fun savedState(){
        activityDetailBinding.catchButton.visibility = View.GONE
        activityDetailBinding.unsaveButton.visibility = View.VISIBLE
    }

    private fun unSavedState(){
        activityDetailBinding.catchButton.visibility = View.VISIBLE
        activityDetailBinding.unsaveButton.visibility = View.GONE
    }

}