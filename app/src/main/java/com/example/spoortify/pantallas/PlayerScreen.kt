package com.example.spoortify.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.RepeatOn
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.ShuffleOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spoortify.shared.ExoPlayerViewModel

@Composable
fun PlayerScreen(id : Int, isAlbum : Boolean, randomSelected: Boolean) {

    val exoPlayerViewModel: ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current
    if(exoPlayerViewModel.playList.value.size == 0){
        exoPlayerViewModel.setIsAlbum(isAlbum)
        exoPlayerViewModel.generarCanciones(id)
    }
    val duracion by exoPlayerViewModel.duracion.collectAsStateWithLifecycle()
    val posicion by exoPlayerViewModel.progreso.collectAsStateWithLifecycle()
    val cancion by exoPlayerViewModel.actual.collectAsStateWithLifecycle()
    val random by exoPlayerViewModel.random.collectAsStateWithLifecycle()
    val loop by exoPlayerViewModel.loop.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        exoPlayerViewModel.crearExoPlayer(contexto)
        exoPlayerViewModel.hacerSonarMusica(contexto)
        exoPlayerViewModel.modoRandom(randomSelected)
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayerViewModel.limpiar()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            "Now playing",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            "${cancion?.nombre} - ${cancion?.album}",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(cancion!!.imagen),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }



        Slider(
            value = (posicion / 1000).toFloat(),
            onValueChange = { newPosition -> val newPositionMillis = (newPosition * 1000).toInt()
                exoPlayerViewModel.actualizarProgreso(newPositionMillis) },
            valueRange = 0F..(duracion / 1000).toFloat(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = exoPlayerViewModel.formatearTiempo(posicion / 1000),
                fontSize = 14.sp
            )
            Text(
                text = exoPlayerViewModel.formatearTiempo(duracion / 1000),
                fontSize = 14.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { exoPlayerViewModel.cambiarRandom(id) }) {
                if(random){
                    Icon(Icons.Filled.ShuffleOn, contentDescription = "")
                }
                else{
                    Icon(Icons.Filled.Shuffle, contentDescription = "")
                }
            }
            IconButton(onClick = { exoPlayerViewModel.CancionPrevia(contexto)}) {
                Icon(Icons.Filled.KeyboardDoubleArrowLeft, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.PausarOSeguirMusica() }) {
                val icon = if (exoPlayerViewModel.exoPlayer.value?.isPlaying == true) {
                    Icons.Filled.Pause
                } else {
                    Icons.Filled.PlayArrow
                }

                Icon(icon, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.SiguienteCancion(contexto)}) {
                Icon(Icons.Filled.KeyboardDoubleArrowRight, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.cambiarLoop() }) {
                if(loop){
                    Icon(Icons.Filled.RepeatOn, contentDescription = "")
                }
                else{
                    Icon(Icons.Filled.Repeat, contentDescription = "")
                }
            }
        }
    }
}