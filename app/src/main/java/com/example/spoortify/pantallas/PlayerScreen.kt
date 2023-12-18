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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.spoortify.R
import com.example.spoortify.shared.ScaffoldVM

@Composable
fun PlayerScreen(viewModelScaffold: ScaffoldVM = viewModel()){
    Column(Modifier.fillMaxSize()) {
        Text("Now playing")
        Text("Nombre de canción")
        Row(horizontalArrangement = Arrangement.Center){
            Image(painter = painterResource(R.drawable.ojo), contentDescription = "", Modifier.fillMaxWidth())
        }
        Slider(value = 0F, onValueChange = {})
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
            Text(text = "0:00")
            Text(text = "2:23")
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Shuffle, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.KeyboardDoubleArrowLeft, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.PlayArrow, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.KeyboardDoubleArrowRight, contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Repeat, contentDescription = "")
            }
        }
    }
}