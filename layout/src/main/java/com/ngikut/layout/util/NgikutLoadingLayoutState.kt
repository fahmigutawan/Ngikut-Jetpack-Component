package com.ngikut.layout.util

import android.annotation.SuppressLint
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ComposableNaming")
@Composable
fun rememberNgikutLoadingLayoutState(
    state:Boolean
):NgikutLoadingLayoutState {
    return remember{
        NgikutLoadingLayoutState(state)
    }.apply {
        this.isLoading.value = state
    }
}

class NgikutLoadingLayoutState(
    isLoading:Boolean
) {
    val isLoading = mutableStateOf(isLoading)
    val snackbarMessage = mutableStateOf("")
    val snackbarAction = mutableStateOf({})
    val showSnackbar = mutableStateOf(false)
    val showSnackbarWithAction = mutableStateOf(false)

    fun showSnackbar(message:String){
        snackbarMessage.value = message
    }

    fun showSnackbarWithAction(message:String, action:() -> Unit){
        snackbarMessage.value = message
        snackbarAction.value = action
    }

    fun resetSnackbarData(){
        snackbarMessage.value = ""
        snackbarAction.value = {}
        showSnackbar.value = false
        showSnackbarWithAction.value = false
    }
}