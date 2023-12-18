package com.example.spoortify.shared

sealed class Rutas (val ruta : String){
    object Player : Rutas("player")
}