package com.kenstarry.harrypotter.core.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    sheetBackground: Color,
    topStartRadius: Dp = 16.dp,
    topEndRadius: Dp = 16.dp,
    sheetContent: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    sheetScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit,
    closeBottomSheet: (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit
) {

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            //  Bottom sheet Icon
            androidx.compose.material3.Icon(
                imageVector = Icons.Outlined.Minimize,
                contentDescription = "Dash icon",
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            sheetContent(modalBottomSheetState, scope)
        },
        content = {
            sheetScope(modalBottomSheetState, scope)
        },
        sheetState = modalBottomSheetState,
        sheetElevation = 0.dp,
        sheetShape = RoundedCornerShape(
            topStart = topStartRadius,
            topEnd = topEndRadius
        ),
        sheetBackgroundColor = sheetBackground
    )
    
    //  to handle back pressed
    BackHandler(
        enabled = modalBottomSheetState.isVisible
    ) {
        closeBottomSheet(modalBottomSheetState, scope)
    }

}