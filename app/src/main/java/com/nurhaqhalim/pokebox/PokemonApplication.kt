package com.nurhaqhalim.pokebox

import android.app.Application
import com.nurhaqhalim.core.di.applicationModule
import com.nurhaqhalim.core.di.databaseModule
import com.nurhaqhalim.core.di.networkModule
import com.nurhaqhalim.pokebox.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PokemonApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@PokemonApplication)
            modules(
                applicationModule,
                networkModule,
                databaseModule,
                viewModelModule
            )
        }
    }
}