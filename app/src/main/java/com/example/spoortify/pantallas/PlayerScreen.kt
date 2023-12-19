package com.example.spoortify.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spoortify.R
import com.example.spoortify.shared.ExoPlayerViewModel
import com.example.spoortify.shared.ScaffoldVM

@Composable
fun PlayerScreen(viewModelScaffold: ScaffoldVM = viewModel()){

    val exoPlayerViewModel: ExoPlayerViewModel = viewModel()
    val contexto = LocalContext.current

    LaunchedEffect(Unit){
        exoPlayerViewModel.crearExoPlayer(contexto)
        exoPlayerViewModel.hacerSonarMusica(contexto)
    }

    Column(Modifier.fillMaxSize()) {
        Text("Now playing")

        Text(exoPlayerViewModel.actual.value.nombre +" - "+ exoPlayerViewModel.actual.value.album)

        Row(horizontalArrangement = Arrangement.Center){
            Image(painter = painterResource(exoPlayerViewModel.actual.value.imagen), contentDescription = "", Modifier.fillMaxWidth())
        }

        Slider(value = exoPlayerViewModel.progreso.value.toFloat(), onValueChange = {})

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Text(text = exoPlayerViewModel.progreso.value.toString())
            Text(text = exoPlayerViewModel.duracion.value.toString())
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Shuffle, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.CancionPrevia(contexto)}) {
                Icon(Icons.Filled.KeyboardDoubleArrowLeft, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.PausarOSeguirMusica() }) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.SiguienteCancion(contexto)}) {
                Icon(Icons.Filled.KeyboardDoubleArrowRight, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Repeat, contentDescription = "")
            }
        }
    }
}