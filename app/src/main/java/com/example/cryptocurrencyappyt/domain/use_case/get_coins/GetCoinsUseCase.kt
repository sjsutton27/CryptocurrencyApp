package com.example.cryptocurrencyappyt.domain.use_case.get_coins

import retrofit2.HttpException
import com.example.cryptocurrencyappyt.common.Resource
import com.example.cryptocurrencyappyt.data.remote.dto.toCoin
import com.example.cryptocurrencyappyt.domain.model.Coin
import com.example.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import okio.IOException
import kotlinx.coroutines.flow.flow

class GetCoinsUseCase(
    private val repository: CoinRepository
){
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow{
        try{
            emit(Resource.Loading())
            val coins = repository.getCoins().map{it.toCoin()}
            emit(Resource.Success(coins))
        }catch(e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }catch(e: IOException){
            emit(Resource.Error("Couldn't reach server, check you internet connection"))
        }

    }
}