package com.grupo3.smi_mobile_client_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.grupo3.smi_mobile_client_android.di.AppContainer
import com.grupo3.smi_mobile_client_android.presentation.screens.detalle.ComunicadoDetalleScreen
import com.grupo3.smi_mobile_client_android.presentation.screens.home.HomeScreen
import com.grupo3.smi_mobile_client_android.presentation.screens.login.LoginScreen

@Composable
fun AppNavigation(appContainer: AppContainer) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRutas.LOGIN) {
        composable(NavRutas.LOGIN) {
            LoginScreen(
                viewModel = appContainer.loginViewModel,
                onLoginSuccess = {
                    navController.navigate(NavRutas.HOME) {
                        popUpTo(NavRutas.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(NavRutas.HOME) {
            HomeScreen(
                viewModel = appContainer.homeViewModel,
                onComunicadoClick = { id -> navController.navigate(NavRutas.detalle(id)) }
            )
        }
        composable(
            route = NavRutas.DETALLE,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id").orEmpty()
            ComunicadoDetalleScreen(
                viewModel = appContainer.crearComunicadoDetalleViewModel(id),
                onVolver = { navController.popBackStack() }
            )
        }
    }
}
