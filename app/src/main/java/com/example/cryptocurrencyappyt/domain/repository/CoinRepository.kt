package com.example.cryptocurrencyappyt.domain.repository

import com.example.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.example.cryptocurrencyappyt.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}