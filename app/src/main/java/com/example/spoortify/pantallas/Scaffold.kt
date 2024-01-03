package com.example.spoortify.pantallas

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PictureInPicture
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BarraInferior(funcionNavegarPlayer: () -> Unit){
    BottomAppBar(modifier = Modifier.fillMaxWidth(),
        containerColor = Color.Cyan) {
        Row(){
            IconButton(onClick = funcionNavegarPlayer, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.Home, contentDescription = "")
            }
            IconButton(onClick = {}, modifier = Modifier.weight(1f)) {
                Icon(Icons.Default.Search, contentDescription = "")
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(titulo : String){
    TopAppBar(title = { Text(titulo) } , modifier = Modifier.fillMaxWidth(), colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Cyan)

    )
}