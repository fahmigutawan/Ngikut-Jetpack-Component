package com.ngikut.layout.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ngikut.layout.util.NgikutLoadingLayoutState
import com.ngikut.layout.util.NgikutLoadingType
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NgikutLoadingLayout(
    modifier: Modifier = Modifier,
    state: NgikutLoadingLayoutState,
    loadingIndicatorColor: Color = Color.Black,
    loadingType: NgikutLoadingType = NgikutLoadingType.FromTop,
    loadingRunningLength: Dp = 64.dp,
    content: @Composable (() -> Unit)
) {
    val scaffoldState = rememberScaffoldState()
    val density = LocalDensity.current
    val scrHeightInPx = density.run { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val scrWidthInPx = density.run { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val loadingContentHeight = remember { mutableStateOf(0f) }
    val loadingContentWidth = remember { mutableStateOf(0f) }
    val startLoadingState = remember { mutableStateOf(false) }
    val loadingProgress = animateFloatAsState(
        targetValue = if (startLoadingState.value) 1f else 0f,
        animationSpec = TweenSpec(durationMillis = 200)
    )
    val loadingAnimateY = animateFloatAsState(
        targetValue = when (loadingType) {
            NgikutLoadingType.FromTop -> if (!startLoadingState.value) (0 - loadingContentHeight.value) else density.run { loadingRunningLength.toPx() }
            NgikutLoadingType.FromBottom -> if (!startLoadingState.value) (scrHeightInPx + loadingContentHeight.value) else density.run { scrHeightInPx - (loadingRunningLength.toPx() * 2) }
            NgikutLoadingType.FromStart -> scrHeightInPx / 2 - loadingContentHeight.value / 2
            NgikutLoadingType.FromEnd -> scrHeightInPx / 2 - loadingContentHeight.value / 2
            NgikutLoadingType.MiddleWithBlurryBackground -> scrHeightInPx / 2 - loadingContentHeight.value / 2
            NgikutLoadingType.Middle -> scrHeightInPx / 2 - loadingContentHeight.value / 2
        },
        animationSpec = TweenSpec(400)
    )
    val loadingAnimateX = animateFloatAsState(
        targetValue = when (loadingType) {
            NgikutLoadingType.FromTop -> scrWidthInPx / 2 - loadingContentWidth.value / 2
            NgikutLoadingType.FromBottom -> scrWidthInPx / 2 - loadingContentWidth.value / 2
            NgikutLoadingType.FromStart -> if (!startLoadingState.value) (0 - loadingContentWidth.value) else density.run { loadingRunningLength.toPx() }
            NgikutLoadingType.FromEnd -> if (!startLoadingState.value) (scrWidthInPx + loadingContentWidth.value) else density.run { scrWidthInPx - (loadingRunningLength.toPx() * 2) }
            NgikutLoadingType.MiddleWithBlurryBackground -> scrWidthInPx / 2 - loadingContentWidth.value / 2
            NgikutLoadingType.Middle -> scrWidthInPx / 2 - loadingContentWidth.value / 2
        },
        animationSpec = TweenSpec(400)
    )

    LaunchedEffect(key1 = state.isLoading.value){
        delay(200)
        startLoadingState.value = state.isLoading.value
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box(modifier = modifier) {
            content()

            if (loadingProgress.value > 0f && loadingType == NgikutLoadingType.MiddleWithBlurryBackground) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = loadingProgress.value * 0.3f))
                        .clickable(
                            enabled = false,
                            onClick = { /*Let this empty, this section used to prevent user from operating/clicking stuffs behind the box*/ }
                        )
                )
            }

            if(state.isLoading.value){
                when {
                    (loadingType == NgikutLoadingType.Middle || loadingType == NgikutLoadingType.MiddleWithBlurryBackground) -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .shadow(
                                        shape = CircleShape,
                                        elevation = 10.dp,
                                        ambientColor = loadingIndicatorColor.copy(alpha = loadingProgress.value),
                                        spotColor = loadingIndicatorColor.copy(alpha = loadingProgress.value)
                                    )
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .onSizeChanged {
                                        density.run {
                                            loadingContentHeight.value = it.height
                                                .toDp()
                                                .toPx()
                                            loadingContentWidth.value = it.width
                                                .toDp()
                                                .toPx()
                                        }
                                    }
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .padding(8.dp),
                                    strokeWidth = 3.dp,
                                    color = loadingIndicatorColor.copy(alpha = loadingProgress.value)
                                )
                            }
                        }
                    }
                    else -> {
                        Layout(
                            content = {
                                Box(
                                    modifier = Modifier
                                        .shadow(
                                            shape = CircleShape,
                                            elevation = 10.dp,
                                            clip = false,
                                            spotColor = Color.Black.copy(alpha = loadingProgress.value),
                                            ambientColor = Color.Black.copy(alpha = loadingProgress.value)
                                        )
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .onSizeChanged {
                                            density.run {
                                                loadingContentHeight.value = it.height
                                                    .toDp()
                                                    .toPx()
                                                loadingContentWidth.value = it.width
                                                    .toDp()
                                                    .toPx()
                                            }
                                        }
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .padding(8.dp),
                                        strokeWidth = 3.dp,
                                        color = loadingIndicatorColor.copy(alpha = loadingProgress.value)
                                    )
                                }
                            },
                            measurePolicy = { measurables, constratins ->
                                val placeable = measurables.map {
                                    it.measure(constratins)
                                }

                                layout(
                                    width = constratins.minWidth,
                                    height = constratins.minHeight
                                ) {
                                    placeable.forEach {
                                        it.place(
                                            loadingAnimateX.value.roundToInt(),
                                            loadingAnimateY.value.roundToInt()
                                        )
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}