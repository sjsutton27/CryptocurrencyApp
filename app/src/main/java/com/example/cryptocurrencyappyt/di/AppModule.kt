package com.example.cryptocurrencyappyt.di

import android.content.Context
import com.example.cryptocurrencyappyt.common.Constants.BASE_URL
import com.example.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyappyt.data.repository.CoinRepositoryImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.cryptocurrencyappyt.domain.repository.CoinRepository
import com.example.cryptocurrencyappyt.domain.use_case.get_coin.GetCoinUseCase


interface AppModule{
    val coinPaprikaApi: CoinPaprikaApi
    val coinRepository: CoinRepository
}

class AppModuleImpl(
    private val appContext: Context
): AppModule{
    override val coinPaprikaApi: CoinPaprikaApi by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    override val coinRepository: CoinRepository by lazy{
        CoinRepositoryImpl(coinPaprikaApi)
    }

}