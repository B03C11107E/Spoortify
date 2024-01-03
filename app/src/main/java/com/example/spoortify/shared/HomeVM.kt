package com.example.spoortify.shared

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeVM : ViewModel(){
    private val _albumes  = MutableStateFlow(getAlbumes())
    val albumes = _albumes.asStateFlow()

    private val _playlists  = MutableStateFlow(getplaylists())
    val playlists = _playlists.asStateFlow()
}