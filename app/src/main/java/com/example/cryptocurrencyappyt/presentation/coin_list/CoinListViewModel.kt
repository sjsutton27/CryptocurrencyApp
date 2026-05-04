package com.example.cryptocurrencyappyt.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyappyt.common.Resource
import com.example.cryptocurrencyappyt.domain.repository.CoinRepository
import com.example.cryptocurrencyappyt.domain.use_case.get_coins.GetCoinsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CoinListViewModel (
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel(){
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init{
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = CoinListState(
                        coins = result.data?: emptyList(),
                    )
                }
                is Resource.Error ->{
                    _state.value = CoinListState(error = result.message?:"Unexpected Error Occurred")
                }
                is Resource.Loading ->{
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
