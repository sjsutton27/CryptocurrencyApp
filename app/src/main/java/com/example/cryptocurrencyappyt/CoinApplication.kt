package com.example.cryptocurrencyappyt

import android.app.Application
import com.example.cryptocurrencyappyt.di.AppModule
import com.example.cryptocurrencyappyt.di.AppModuleImpl
class CoinApplication : Application(){

    companion object{
        lateinit var appModule: AppModule
    }

    override fun onCreate(){
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}