package com.ngikut.layout.util

import android.annotation.SuppressLint
import androidx.compose.material.SnackbarHostState
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
    var snackbarAction:(SnackbarHostState) -> Unit by mutableStateOf({})
    var snackbarActionLabel by mutableStateOf("")
    var isSnackbarActive by mutableStateOf(false)
    var isSnackbarWithActionActive by mutableStateOf(false)

    fun showSnackbar(message:String){
        isSnackbarActive = true
        snackbarMessage = message
    }

    fun showSnackbarWithAction(message:String, actionLabel:String, action:(SnackbarHostState) -> Unit){
        isSnackbarWithActionActive = true
        snackbarMessage = message
        snackbarAction = action
        snackbarActionLabel = actionLabel
    }

    fun resetSnackbarData(){
        snackbarMessage = ""
        snackbarActionLabel = ""
        snackbarAction = {}
        isSnackbarActive = false
        isSnackbarWithActionActive = false
    }
}