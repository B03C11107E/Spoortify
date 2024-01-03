package com.example.spoortify.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spoortify.shared.HomeVM
import com.example.spoortify.shared.Rutas

@Composable
fun HomeScreen(navController: NavController) {

    val homeViewModel: HomeVM = viewModel()

    Column {
        Text(text = "Albumes", fontSize = 20.sp)
        LazyRow {
            items(homeViewModel.albumes.value) { album ->
                    Card(modifier = Modifier.padding(8.dp).clickable{
                        navController.navigate(Rutas.Album.ruta+"/${album.id}/true")
                    }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = album.imagen),
                                contentDescription = "",
                                modifier = Modifier.size(150.dp)
                            )
                            Text(text = album.nombre)
                        }
                    }
                }
        }
        Text(text = "Playlists", fontSize = 20.sp)

        LazyRow {
            items(homeViewModel.playlists.value) { playlist ->
                    Card(modifier = Modifier.padding(8.dp).clickable{
                        navController.navigate(Rutas.Album.ruta+"/${playlist.id}/false")
                    }) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = playlist.imagen),
                                contentDescription = "",
                                modifier = Modifier.size(150.dp)
                            )
                            Text(text = playlist.nombre)
                        }
                    }
            }
        }
    }
}
