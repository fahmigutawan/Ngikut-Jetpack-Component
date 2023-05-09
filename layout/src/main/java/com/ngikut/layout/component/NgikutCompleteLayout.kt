package com.ngikut.layout.component

import android.annotation.SuppressLint
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NgikutCompleteLayout(
    modifier:Modifier = Modifier
) {
    BottomSheetScaffold(
        modifier = modifier.nestedScroll(
            connection =
        ),
        scaffoldState =,
        sheetContent =,
        topBar =,
        floatingActionButton =,
        floatingActionButtonPosition =,
        sheetGesturesEnabled =,
        sheetShape =,
        sheetElevation =,
        sheetBackgroundColor =,
        sheetContentColor =,
        sheetPeekHeight =,
        drawerContent =,
        drawerGesturesEnabled =,
        drawerShape =,
        drawerElevation =,
        drawerBackgroundColor =,
        drawerContentColor =,
        drawerScrimColor =,
        backgroundColor =,
        contentColor =,
    ) {
        Scaffold(
            bottomBar =
        ) {

        }
    }
}