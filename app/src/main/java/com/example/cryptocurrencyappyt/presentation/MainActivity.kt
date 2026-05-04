package com.example.cryptocurrencyappyt.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.cryptocurrencyappyt.presentation.coin_list.CoinListViewModel
import com.example.cryptocurrencyappyt.presentation.ui.theme.CryptocurrencyAppYTTheme
import com.example.cryptocurrencyappyt.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocurrencyappyt.presentation.coin_detail.CoinDetailViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyappyt.presentation.coin_detail.CoinDetailScreen
import com.example.cryptocurrencyappyt.presentation.coin_list.CoinListScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.cryptocurrencyappyt.CoinApplication
import com.example.cryptocurrencyappyt.CoinApplication.Companion.appModule
import com.example.cryptocurrencyappyt.domain.repository.CoinRepository
import com.example.cryptocurrencyappyt.domain.use_case.get_coin.GetCoinUseCase


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val coinRepository = appModule.coinRepository


        setContent {
            CryptocurrencyAppYTTheme {
                val viewListModel = viewModel<CoinListViewModel>(
                    factory = viewModelFactory {
                        CoinListViewModel(
                            getCoinsUseCase = GetCoinsUseCase(coinRepository)
                        )
                    }
                )

                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ){
                        composable(
                            route = Screen.CoinListScreen.route
                        ){
                            CoinListScreen(navController, viewListModel)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}",
                            arguments = listOf(navArgument("coinId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val coinId = backStackEntry.arguments?.getString("coinId")
                            Log.d("NAVGRAPH", "coinId from backStackEntry: $coinId")
                            val viewDetailModel: CoinDetailViewModel = viewModel(
                                factory = viewModelFactory {
                                    CoinDetailViewModel(
                                        getCoinUseCase = GetCoinUseCase(coinRepository),
                                        coinId = coinId?:""
                                    )
                                }
                            )

                            CoinDetailScreen(viewModel = viewDetailModel)
                        }



                    }
                }
            }
        }
    }
}