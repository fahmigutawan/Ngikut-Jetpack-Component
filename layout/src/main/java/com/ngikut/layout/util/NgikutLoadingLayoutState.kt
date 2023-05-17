package com.ngikut.layout.util

import android.annotation.SuppressLint
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
}