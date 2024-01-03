package com.example.spoortify.shared

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.annotation.AnyRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.spoortify.entities.cancion
import com.example.spoortify.entities.playlist
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ExoPlayerViewModel() : ViewModel(){

    // El reproductor de musica, empieza a null
    private val _exoPlayer : MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    val exoPlayer = _exoPlayer.asStateFlow()

    private val _isAlbum = MutableStateFlow(true)
    val isAlbum = _isAlbum.asStateFlow()


    private val _index  = MutableStateFlow(0)
    val index = _index.asStateFlow()



    // La duración de la canción
    private val _duracion  = MutableStateFlow(0)
    val duracion = _duracion.asStateFlow()

    // El progreso (en segundos) actual de la cancion
    private val _progreso = MutableStateFlow(0)
    val progreso = _progreso.asStateFlow()

    private val _random = MutableStateFlow(false)
    val random = _random.asStateFlow()

    private val _loop = MutableStateFlow(false)
    val loop = _loop.asStateFlow()

    private val _playList = MutableStateFlow(mutableListOf<cancion>())
    val playList = _playList.asStateFlow()

    // La cancion actual que está sonando
    private val _actual  = MutableStateFlow<cancion?>(null)
    val actual = _actual.asStateFlow()

    fun setIsAlbum(isAlbum : Boolean){
        _isAlbum.value = isAlbum
    }
    fun modoRandom(random : Boolean){
        _random.value = random
        if(random){
            _playList.value.shuffle()
        }
    }
    fun generarCanciones(id : Int){
        if(_isAlbum.value){
            _playList.value = getAlbumSongs(id).toMutableList()
        }
        else{
            _playList.value = getplaylistSongs(id).toMutableList()
        }
        _actual.value = _playList.value[_index.value]
    }

    fun crearExoPlayer(context: Context){
        _exoPlayer.value = ExoPlayer.Builder(context).build()
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }


    fun hacerSonarMusica(context: Context){

        // Este listener se mantendrá mientras NO se librere el _exoPlayer
        // Asi que no hace falta crearlo más de una vez.
        var cancion = MediaItem.fromUri(obtenerRuta(context, actual.value!!.cancion))
        _exoPlayer.value!!.setMediaItem(cancion)
        _exoPlayer.value!!.playWhenReady = true
        _exoPlayer.value!!.pause()
        _exoPlayer.value!!.addListener(object : Player.Listener{
            override fun onPlaybackStateChanged(playbackState: Int) {
                if(playbackState == Player.STATE_READY){
                    _duracion.value = _exoPlayer.value!!.duration.toInt()

                    viewModelScope.launch {
                        while (isActive) {
                            _progreso.value = _exoPlayer.value!!.currentPosition.toInt()
                            delay(1000)
                        }
                    }
                }
                else if(playbackState == Player.STATE_BUFFERING){
                    // El Player está cargando el archivo, preparando la reproducción.
                    // No está listo, pero está en ello.
                }
                else if(playbackState == Player.STATE_ENDED){
                    // El Player ha terminado de reproducir el archivo.
                    SiguienteCancion(context)

                }
                else if(playbackState == Player.STATE_IDLE){
                    // El player se ha creado, pero no se ha lanzado la operación prepared.
                }

            }
        }
        )


    }

    // Este método se llama cuando el VM se destruya.
    override fun onCleared() {
        _exoPlayer.value!!.release()
        super.onCleared()
    }
    fun limpiar() {
        _exoPlayer.value?.release()
    }

    fun PausarOSeguirMusica() {
        if(_exoPlayer.value!!.isPlaying){
            _exoPlayer.value!!.pause()
        }
        else{
            _exoPlayer.value!!.play()
        }
    }

    fun SiguienteCancion(context: Context) {

        _exoPlayer.value!!.stop()
        _exoPlayer.value!!.clearMediaItems()
        if(_index.value == _playList.value.size-1){
            if(_loop.value){
                _index.value = 0
            }
        }
        else{
            _index.value++
        }
        _actual.value = _playList.value.get(_index.value)
        _exoPlayer.value!!.setMediaItem(MediaItem.fromUri(obtenerRuta(context, _actual.value!!.cancion)))
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }
    fun CancionPrevia(context: Context) {

        _exoPlayer.value!!.stop()
        _exoPlayer.value!!.clearMediaItems()
        if(_index.value == 0){
            if(_loop.value){
                _index.value = _playList.value.size-1
            }
        }
        else{
            _index.value--
        }
        _actual.value = _playList.value.get(_index.value)
        _exoPlayer.value!!.setMediaItem(MediaItem.fromUri(obtenerRuta(context, _actual.value!!.cancion)))
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }
    fun formatearTiempo(tiempoSegundos: Int): String {
        val minutos = TimeUnit.SECONDS.toMinutes(tiempoSegundos.toLong())
        val segundos = tiempoSegundos - TimeUnit.MINUTES.toSeconds(minutos)
        return String.format("%02d:%02d", minutos, segundos)
    }
    fun actualizarProgreso(tiempoMilis: Int){
        _exoPlayer.value!!.seekTo(tiempoMilis.toLong())
        _progreso.value = _exoPlayer.value!!.currentPosition.toInt()
    }
    fun cambiarRandom(id : Int){
        _random.value = !_random.value
        if(_random.value){
            generarPlayListRandom()
        }
        else{
            generarCanciones(id)
        }
    }
    fun cambiarLoop(){
        _loop.value = !_loop.value
    }
    fun generarPlayListRandom() {
        _playList.value.shuffle()
    }
}

// Funcion auxiliar que devuelve la ruta de un fichero a partir de su ID
@Throws(Resources.NotFoundException::class)
fun obtenerRuta(context: Context, @AnyRes resId: Int): Uri {
    val res: Resources = context.resources
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId)
    )
}
