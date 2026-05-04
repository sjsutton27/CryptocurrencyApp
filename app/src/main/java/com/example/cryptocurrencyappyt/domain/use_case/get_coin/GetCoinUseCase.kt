package com.example.cryptocurrencyappyt.domain.use_case.get_coin

import android.util.Log
import retrofit2.HttpException
import com.example.cryptocurrencyappyt.common.Resource

import com.example.cryptocurrencyappyt.data.remote.dto.toCoinDetail
import com.example.cryptocurrencyappyt.domain.model.CoinDetail
import com.example.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import okio.IOException
import kotlinx.coroutines.flow.flow

class GetCoinUseCase(
    private val repository: CoinRepository
){
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow{
        try {
            emit(Resource.Loading())

            val coinDto = repository.getCoinById(coinId)
            val coin = coinDto.toCoinDetail()

            if (coin.coinId.isBlank()) {
                Log.e("GetCoinUseCase", "Coin ID is blank")
            }
            if (coin.name.isBlank()) {
                Log.e("GetCoinUseCase", "Coin name is blank")
            }
            if (coin.description.isBlank()) {
                Log.w("GetCoinUseCase", "Coin description is empty")
            }
            if (coin.tags.isEmpty()) {
                Log.w("GetCoinUseCase", "Coin tags list is empty")
            }
            if (coin.team.isEmpty()) {
                Log.w("GetCoinUseCase", "Coin team list is empty")
            }

            emit(Resource.Success(coin))
        }catch(e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e: IOException){
            emit(Resource.Error("Couldn't reach server, check you internet connection"))
        }

    }
}