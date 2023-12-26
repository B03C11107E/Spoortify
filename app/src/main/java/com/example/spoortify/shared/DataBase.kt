package com.example.spoortify.shared

import com.example.spoortify.R
import com.example.spoortify.entities.cancion

private val listaCanciones  = listOf(
    cancion("Castle Vein", "UltraKill", R.raw.castlevein, R.drawable.ultrakill),
    cancion("The cyber grind", "UltraKill", R.raw.thecybergrind, R.drawable.ultrakill),
    cancion("Altars of Apostasy", "UltraKill", R.raw.altarsofapostasy, R.drawable.ultrakill),
    cancion("M.O.O.N", "Hotline miami", R.raw.moon, R.drawable.hotlinemiami),
    cancion("Roller Mobster", "Hotline miami", R.raw.rollermobster, R.drawable.hotlinemiami)
)
fun getCancion(index: Int) : cancion {
    return listaCanciones.get(index)
}