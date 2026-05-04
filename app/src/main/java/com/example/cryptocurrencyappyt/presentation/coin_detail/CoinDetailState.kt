package com.example.cryptocurrencyappyt.presentation.coin_detail

import com.example.cryptocurrencyappyt.domain.model.Coin
import com.example.cryptocurrencyappyt.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
