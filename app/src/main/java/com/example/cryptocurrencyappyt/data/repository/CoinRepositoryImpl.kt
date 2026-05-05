package com.example.cryptocurrencyappyt.data.repository

import android.util.Log
import com.example.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.example.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.example.cryptocurrencyappyt.domain.repository.CoinRepository

class CoinRepositoryImpl(
    private val api: CoinPaprikaApi
) : CoinRepository{
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoin()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}