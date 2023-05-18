package com.ngikut.layout.util

import android.annotation.SuppressLint
import androidx.compose.runtime.*

@SuppressLint("ComposableNaming")
@Composable
fun rememberNgikutLoadingLayoutState(
    state:Boolean
):NgikutLoadingLayoutState {
    return remember{
        NgikutLoadingLayoutState(state)
    }.apply {
        this.isLoading = state
    }
}

class NgikutLoadingLayoutState(
    isLoading:Boolean
) {
    var isLoading by mutableStateOf(isLoading)
    var snackbarMessage by mutableStateOf("")
    var snackbarAction by mutableStateOf({})
    var isSnackbarActive by mutableStateOf(false)
    var isSnackbarWithActionActive by mutableStateOf(false)

    fun showSnackbar(message:String){
        snackbarMessage = message
    }

    fun showSnackbarWithAction(message:String, action:() -> Unit){
        snackbarMessage = message
        snackbarAction = action
    }

    fun resetSnackbarData(){
        snackbarMessage = ""
        snackbarAction = {}
        isSnackbarActive = false
        isSnackbarWithActionActive = false
    }
}