package com.kenstarry.harrypotter.feature_home.presentation.components

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.components.CoilImage
import com.kenstarry.harrypotter.core.presentation.components.PillBtn

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailBottomSheet(
    character: CharacterModel
) {

    val context = LocalContext.current

    val aliasListState = rememberLazyStaggeredGridState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
        ) {


            //  image section
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                //  if the character is a wizard, show a star icon
                if (character.wizard) {

                    //  wizard image
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                    ) {

                        CoilImage(
                            context = context,
                            imageUri = character.image.toUri(),
                            placeholder = com.kenstarry.harrypotter.R.drawable.ic_launcher_background,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(120.dp)
                                .border(
                                    width = 3.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = CircleShape
                                )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {

                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(30.dp)
                                    .background(MaterialTheme.colorScheme.onPrimary),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Outlined.StarRate,
                                    contentDescription = "Star",
                                    tint = MaterialTheme.colorScheme.primary
                                )

                            }

                        }

                    }

                } else {
                    //  image
                    CoilImage(
                        context = context,
                        imageUri = character.image.toUri(),
                        placeholder = com.kenstarry.harrypotter.R.drawable.ic_launcher_background,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(120.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                //  name
                Text(
                    text = character.name,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.secondary
                )

            }

            //  house section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PillBtn(
                    title = character.house,
                    verticalPadding = 8.dp
                ) {

                }
            }

        }

        //  show aliases if there is a value in aliases
        if (character.alternate_names.isNotEmpty()) {

            Spacer(modifier = Modifier.height(24.dp))

            //  aliases
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                //  name
                Text(
                    text = "Alias",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )

                LazyHorizontalStaggeredGrid(
                    rows = StaggeredGridCells.Fixed(1),
                    content = {
                        items(character.alternate_names) { name ->
                            PillBtn(
                                title = name,
                                textColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
                                containerColor = MaterialTheme.colorScheme.onSecondary,
                                horizontalPadding = 16.dp,
                                verticalPadding = 8.dp,
                                onClick = {}
                            )

                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    },
                    state = aliasListState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    horizontalArrangement = Arrangement.Center
                )

            }
        }
    }

}