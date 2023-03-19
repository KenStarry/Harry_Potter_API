package com.kenstarry.harrypotter.feature_home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.kenstarry.harrypotter.core.domain.model.CharacterModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WizardsSection(
    modifier: Modifier = Modifier,
    allWizards: List<CharacterModel>
) {

    val pagerState = rememberPagerState()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  title
        Text(
            text = "Wizards",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
        )

        //  horizontal pager
        HorizontalPager(
            count = allWizards.size,
            state = pagerState,
            itemSpacing = 16.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            CharacterItem(character = allWizards[it])
        }

        //  horizontal indicator
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.primary,
            inactiveColor = MaterialTheme.colorScheme.tertiary,
            spacing = 8.dp,
            indicatorWidth = 5.dp,
            indicatorHeight = 5.dp,
            indicatorShape = CircleShape
        )

    }
}


















