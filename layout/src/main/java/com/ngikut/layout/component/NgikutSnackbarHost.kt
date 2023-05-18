package com.ngikut.layout.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun NgikutSnackbarHost(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = Color.White,
    elevation: Dp = 8.dp,
    hostState: SnackbarHostState
) {
    SnackbarHost(hostState = hostState) {
        Box(
            modifier = modifier
                .shadow(elevation = elevation, shape = shape)
                .clip(shape)
                .background(backgroundColor)
        ) {

        }
    }
}

@Composable
fun NgikutSnackbarHost(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    backgroundColor: Color = Color.White,
    elevation: Dp = 8.dp,
    action: @Composable () -> Unit,
    hostState: SnackbarHostState
) {
    SnackbarHost(hostState = hostState) {
        Box(
            modifier = modifier
                .shadow(elevation = elevation, shape = shape)
                .clip(shape)
                .background(backgroundColor)
        ) {

        }
    }
}