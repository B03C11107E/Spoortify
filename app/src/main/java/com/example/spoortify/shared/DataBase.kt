package com.example.spoortify.shared

import com.example.spoortify.R
import com.example.spoortify.entities.cancion

private val listaCanciones  = listOf(
    cancion("From The Stars", "Unicorn on Ketamine", R.raw.fromthestars, R.drawable.ojo),
    cancion("Griddy", "Hits", R.raw.griddy, R.drawable.ojo),
    cancion("Kalinka", "Unicorn on Ketamine", R.raw.kalinka, R.drawable.ojo),
    cancion("MBKetamine", "Unicorn on Ketamine", R.raw.mbketamine, R.drawable.ojo),
    cancion("Not For me", "Unicorn on Ketamine", R.raw.notforme, R.drawable.ojo)
)
fun getCancion(index: Int) : cancion {
    return listaCanciones.get(index)
}