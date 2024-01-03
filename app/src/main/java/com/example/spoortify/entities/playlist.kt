package com.example.spoortify.entities

data class playlist (
    val id : Int,
    val nombre : String,
    val fechaDeCreacion : Int,
    val canciones : List<cancion>,
    val imagen : Int
)
