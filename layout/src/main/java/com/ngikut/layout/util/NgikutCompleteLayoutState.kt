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
    state:Boolean,
    loadingRunningLength: Dp = 64.dp
):NgikutCompleteLayoutState {
    return remember{
        NgikutCompleteLayoutState(state, loadingRunningLength)
    }.apply {
        this.isLoading.value = state
        this.loadingRunningLength.value = loadingRunningLength
    }
}

class NgikutCompleteLayoutState(
    isLoading:Boolean,
    loadingRunningLength: Dp
) {
    val isLoading = mutableStateOf(isLoading)
    val loadingRunningLength = mutableStateOf(loadingRunningLength)
}