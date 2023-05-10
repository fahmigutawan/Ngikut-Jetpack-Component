package com.ngikut.layout.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.ngikut.layout.util.NgikutCompleteLayoutState
import com.ngikut.layout.util.NgikutLoadingType
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NgikutCompleteLayout(
    modifier: Modifier = Modifier,
    state: NgikutCompleteLayoutState,
    runDuration: Int = 400,
    loadingIndicatorColor: Color = Color.Black,
    loadingType: NgikutLoadingType = NgikutLoadingType.FromTop,
    content: @Composable (() -> Unit)
) {
    val density = LocalDensity.current
    val scrHeightInPx = density.run { LocalConfiguration.current.screenHeightDp.dp.toPx() }
    val scrWidthInPx = density.run { LocalConfiguration.current.screenWidthDp.dp.toPx() }
    val loadingContentHeight = remember { mutableStateOf(0f) }
    val loadingContentWidth = remember { mutableStateOf(0f) }
    val loadingProgress = animateFloatAsState(
        targetValue = if (!state.isLoading.value) 0f else 1f,
        animationSpec = TweenSpec(durationMillis = 200)
    )
    val loadingAnimateY = animateFloatAsState(
        targetValue = when (loadingType) {
            NgikutLoadingType.FromTop -> if (!state.isLoading.value) (0 - loadingContentHeight.value) else density.run { state.loadingRunningLength.value.toPx() }
            NgikutLoadingType.FromBottom -> if (!state.isLoading.value) (scrHeightInPx + loadingContentHeight.value) else density.run { scrHeightInPx - (state.loadingRunningLength.value.toPx() * 2) }
            NgikutLoadingType.FromStart -> scrHeightInPx / 2 - loadingContentHeight.value / 2
            NgikutLoadingType.FromEnd -> scrHeightInPx / 2 - loadingContentHeight.value / 2
            NgikutLoadingType.MiddleWithBlurryBackground -> scrHeightInPx / 2 - loadingContentHeight.value / 2
            NgikutLoadingType.Middle -> scrHeightInPx / 2 - loadingContentHeight.value / 2
        },
        animationSpec = TweenSpec(runDuration)
    )
    val loadingAnimateX = animateFloatAsState(
        targetValue = when (loadingType) {
            NgikutLoadingType.FromTop -> scrWidthInPx / 2 - loadingContentWidth.value / 2
            NgikutLoadingType.FromBottom -> scrWidthInPx / 2 - loadingContentWidth.value / 2
            NgikutLoadingType.FromStart -> if (!state.isLoading.value) (0 - loadingContentWidth.value) else density.run { state.loadingRunningLength.value.toPx() }
            NgikutLoadingType.FromEnd -> if (!state.isLoading.value) (scrWidthInPx + loadingContentWidth.value) else density.run { scrWidthInPx - (state.loadingRunningLength.value.toPx() * 2) }
            NgikutLoadingType.MiddleWithBlurryBackground -> scrWidthInPx / 2 - loadingContentWidth.value / 2
            NgikutLoadingType.Middle -> scrWidthInPx / 2 - loadingContentWidth.value / 2
        },
        animationSpec = TweenSpec(runDuration)
    )

//    BottomSheetScaffold(
//        modifier = modifier,
//        scaffoldState =,
//        sheetContent =,
//        topBar =,
//        floatingActionButton =,
//        floatingActionButtonPosition =,
//        sheetGesturesEnabled =,
//        sheetShape =,
//        sheetElevation =,
//        sheetBackgroundColor =,
//        sheetContentColor =,
//        sheetPeekHeight =,
//        drawerContent =,
//        drawerGesturesEnabled =,
//        drawerShape =,
//        drawerElevation =,
//        drawerBackgroundColor =,
//        drawerContentColor =,
//        drawerScrimColor =,
//        backgroundColor =,
//        contentColor =,
//    ) {
    Scaffold(
//            bottomBar =
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