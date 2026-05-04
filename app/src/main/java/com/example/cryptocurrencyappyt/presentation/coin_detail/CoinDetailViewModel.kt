package com.example.cryptocurrencyappyt.presentation.coin_detail

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyappyt.common.Resource
import com.example.cryptocurrencyappyt.domain.use_case.get_coin.GetCoinUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinDetailViewModel (
    private val getCoinUseCase: GetCoinUseCase,
    coinId: String
) : ViewModel(){
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init{
        Log.d("CoinDetailViewModel", "coinId: $coinId")
        getCoin(coinId)
    }

    private fun getCoin(coinId:String){
        getCoinUseCase(coinId = coinId).onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error ->{
                    _state.value = CoinDetailState(error = result.message?:"Unexpected Error Occurred")
                }
                is Resource.Loading ->{
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
