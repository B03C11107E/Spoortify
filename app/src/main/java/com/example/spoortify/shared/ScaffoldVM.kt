package com.example.spoortify.shared

import androidx.lifecycle.ViewModel
import com.example.spoortify.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScaffoldVM : ViewModel() {

    private val _titulo = MutableStateFlow("Player")
    val titulo = _titulo.asStateFlow()


    // Función que actualiza el título del Top bar
    fun modificarTitulo(nuevoTitulo : String){
        _titulo.value = nuevoTitulo
    }


    private val _cancionActual = MutableStateFlow(R.drawable.ic_launcher_foreground)
    val cancionActual = _cancionActual.asStateFlow()


    // Función que actualiza la canción actual
    fun modificarCancion(){
        if(_cancionActual.value == R.drawable.ic_launcher_foreground) _cancionActual.value = R.drawable.ic_launcher_foreground
        else _cancionActual.value = R.drawable.ic_launcher_foreground
    }
}