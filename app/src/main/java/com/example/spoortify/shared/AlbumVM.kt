package com.example.spoortify.shared

import androidx.lifecycle.ViewModel
import androidx.media3.exoplayer.ExoPlayer
import com.example.spoortify.entities.album
import com.example.spoortify.entities.playlist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AlbumVM(isAlbum: Boolean) : ViewModel(){



    private val _albumActual  = MutableStateFlow<album?>(null)
    val albumActual = _albumActual.asStateFlow()

    private val _playListActual  = MutableStateFlow<playlist?>(null)
    val playListActual = _playListActual.asStateFlow()

    private val _isAlbum = MutableStateFlow(isAlbum)
    val isAlbum = _isAlbum.asStateFlow()
    fun encontrarAlbum(int: Int){
        if(_isAlbum.value){
            _albumActual.value = getAlbum(int)
        }
        else{
            _playListActual.value = getplaylist(int)
        }
    }
}