package com.example.spoortify.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spoortify.pantallas.AlbumScreen
import com.example.spoortify.pantallas.BarraInferior
import com.example.spoortify.pantallas.BarraSuperior
import com.example.spoortify.pantallas.HomeScreen
import com.example.spoortify.pantallas.PlayerScreen
import com.example.spoortify.shared.Rutas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrafoNavegacion() {

    val navController = rememberNavController()

    val entradaNavActual by navController.currentBackStackEntryAsState()

    val rutaActual = entradaNavActual?.destination?.route

    Scaffold(topBar = { BarraSuperior(titulo = "Spoortify")},
        bottomBar = { BarraInferior(funcionNavegarPlayer = {
                if(rutaActual != Rutas.Home.ruta){
                    navController.navigate(Rutas.Home.ruta)
                }

            })},
        content = {
                paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(navController = navController, startDestination = Rutas.Home.ruta){
                    composable(Rutas.Home.ruta){
                        HomeScreen(navController)
                    }
                    composable(Rutas.Album.ruta+"/{id}/{isAlbum}"){
                        val id = it.arguments?.getString("id")?.toInt()
                        val isAlbum = it.arguments?.getString("isAlbum").toBoolean()
                        if (id != null) {
                            AlbumScreen(id, isAlbum, navController)
                        }
                    }
                    composable(Rutas.Player.ruta+"/{id}/{isAlbum}/{random}"){
                        val id = it.arguments?.getString("id")!!.toInt()
                        val isAlbum = it.arguments?.getString("isAlbum").toBoolean()
                        val random = it.arguments?.getString("random").toBoolean()
                        if (id != null) {
                            PlayerScreen(id, isAlbum, random)
                        }
                    }
                }

            }

        })
}

