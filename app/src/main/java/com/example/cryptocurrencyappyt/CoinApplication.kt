package com.example.cryptocurrencyappyt

import android.app.Application
import com.example.cryptocurrencyappyt.di.AppModule
import com.example.cryptocurrencyappyt.di.AppModuleImpl
//import com.example.cryptocurrencyappyt.di.appModule
import timber.log.Timber

class CoinApplication : Application(){

    companion object{
        lateinit var appModule: AppModule
    }

    override fun onCreate(){
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}