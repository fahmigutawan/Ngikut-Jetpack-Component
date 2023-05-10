package com.ngikut.layout.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("ComposableNaming")
@Composable
fun rememberNgikutCompleteLayoutState(
    initialState:Boolean,
    loadingRunningLength: Dp = 64.dp
):NgikutCompleteLayoutState {
    return remember{
        NgikutCompleteLayoutState(initialState, loadingRunningLength)
    }.apply {
        this.isLoading.value = initialState
        this.loadingRunningLength.value = loadingRunningLength
    }
}

class NgikutCompleteLayoutState(
    isLoading:Boolean,
    loadingRunningLength: Dp
) {
    val isLoading = mutableStateOf(isLoading)
    val initialValue = isLoading
    val loadingRunningLength = mutableStateOf(loadingRunningLength)
}