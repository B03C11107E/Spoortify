package com.example.spoortify.entities

data class album (
    val id : Int,
    val nombre : String,
    val aniodeSalida : Int,
    val artista : String,
    val canciones : List<cancion>,
    val imagen : Int
)