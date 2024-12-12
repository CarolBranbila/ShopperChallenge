package br.com.estudo.bora

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.estudo.bora.confirm.ConfirmArg
import br.com.estudo.bora.confirm.ConfirmScreen
import br.com.estudo.bora.confirm.RiderArg
import br.com.estudo.bora.home.HomeScreen
import br.com.estudo.bora.home.HomeViewModel
import kotlinx.serialization.Serializable

@Serializable
data object Home

@Serializable
data object Confirm

@Serializable
data object History

// https://developer.android.com/guide/navigation/use-graph/navigate#example
@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeScreen(
                viewModel = HomeViewModel(),
                onSuccess = { riders ->
                    navController
                            .navigate(
                                route = Confirm,
                            )
                }
            )
        }

        composable<Confirm> {
            ConfirmScreen()
        }
    }
}