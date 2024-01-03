package com.example.spoortify.pantallas

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.spoortify.shared.AlbumVM
import com.example.spoortify.shared.ExoPlayerViewModel
import com.example.spoortify.shared.Rutas

@Composable
fun AlbumScreen(id: Int, isAlbum: Boolean, navController: NavController) {

    val albumVM = AlbumVM(isAlbum)
    albumVM.encontrarAlbum(id)
    if(albumVM.isAlbum.value){
        Column{
            Image(
                painter = painterResource(albumVM.albumActual.value!!.imagen),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(1F, false)
                    .fillMaxSize()
            )
            Box(modifier = Modifier.weight(2F)){
                Column {
                    Text(text = albumVM.albumActual.value!!.nombre, fontSize = 20.sp)
                    Text(text = "Album de ${albumVM.albumActual.value!!.artista} - ${albumVM.albumActual.value!!.aniodeSalida}", fontSize = 20.sp)
                    Button(onClick = { navController.navigate(Rutas.Player.ruta+"/${id}/true/true") }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Modo aleatorio")
                    }
                    LazyColumn{
                        items(albumVM.albumActual.value!!.canciones){ cancion->
                            Text(text = "${cancion.nombre}\n${cancion.album}", modifier = Modifier
                                .clickable { navController.navigate(Rutas.Player.ruta+"/${id}/true/false")}
                                .padding(8.dp)
                                .fillMaxWidth())
                        }
                    }
                }
            }

        }
    }
    else{
        Column {
            Image(
                painter = painterResource(albumVM.playListActual.value!!.imagen),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .weight(1F, false)
                    .fillMaxSize()
            )
            Box(modifier = Modifier.weight(2F)){
                Column {
                    Text(text = albumVM.playListActual.value!!.nombre, fontSize = 20.sp)
                    Text(
                        text = "Playlist - ${albumVM.playListActual.value!!.fechaDeCreacion}",
                        fontSize = 20.sp
                    )
                    Button(onClick = { navController.navigate(Rutas.Player.ruta+"/${id}/false/true") }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Modo aleatorio")
                    }
                    LazyColumn {
                        items(albumVM.playListActual.value!!.canciones) { cancion ->
                            Text(
                                text = "${cancion.nombre}\n${cancion.album}",
                                modifier = Modifier
                                    .clickable { navController.navigate(Rutas.Player.ruta+"/${id}/false/false")}
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}