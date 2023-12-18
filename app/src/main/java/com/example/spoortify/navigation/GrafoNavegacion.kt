package com.example.spoortify.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spoortify.pantallas.PlayerScreen
import com.example.spoortify.shared.Rutas
import com.example.spoortify.shared.ScaffoldVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrafoNavegacion() {

    val navController = rememberNavController()

    // El parametro route nos devuelve, en formato string, la ruta actual.
    val entradaNavActual by navController.currentBackStackEntryAsState()

    val rutaActual = entradaNavActual?.destination?.route

    // View model general que controla diversos valores del Scaffold -> el título y la barra de navegación
    val viewModelScaffold: ScaffoldVM = viewModel()
    NavHost(navController = navController, startDestination = Rutas.Player.ruta){
        composable(Rutas.Player.ruta){
            PlayerScreen(viewModelScaffold)
        }
    }
}

