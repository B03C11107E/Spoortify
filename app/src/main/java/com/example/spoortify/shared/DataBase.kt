package com.example.spoortify.shared

import com.example.spoortify.R
import com.example.spoortify.entities.album
import com.example.spoortify.entities.cancion
import com.example.spoortify.entities.playlist

private val listaCanciones  = listOf(
    cancion("Castle Vein", "UltraKill", R.raw.castlevein, R.drawable.ultrakill),
    cancion("The cyber grind", "UltraKill", R.raw.thecybergrind, R.drawable.ultrakill),
    cancion("Altars of Apostasy", "UltraKill", R.raw.altarsofapostasy, R.drawable.ultrakill),
    cancion("M.O.O.N", "Hotline miami", R.raw.moon, R.drawable.hotlinemiami),
    cancion("Roller Mobster", "Hotline miami", R.raw.rollermobster, R.drawable.hotlinemiami)
)
 private val albumes = listOf(
     album(0, "Ultrakill OST", 2023, "Ultrakill", listOf(listaCanciones[0], listaCanciones[1], listaCanciones[2]), R.drawable.ultrakill),
     album(1, "Hotline miami OST", 2015, "Varios artistas", listOf(listaCanciones[3], listaCanciones[4]),R.drawable.hotlinemiami)
 )
private val playlists = listOf(
    playlist(0, "Mejores canciones", 2023 , listOf(listaCanciones[2], listaCanciones[1], listaCanciones[4]), R.drawable.wan),
)
fun getCancion(index: Int) : cancion {
    return listaCanciones.get(index)
}
fun getAlbumes() : List<album> {
    return albumes
}fun getplaylists() : List<playlist> {
    return playlists
}
fun getAlbum(id : Int) : album {
    return albumes[id]
}fun getplaylist(id : Int) : playlist {
    return playlists[id]
}
fun getAlbumSongs(id : Int) : List<cancion> {
    return albumes[id].canciones
}fun getplaylistSongs(id : Int) : List<cancion> {
    return playlists[id].canciones
}