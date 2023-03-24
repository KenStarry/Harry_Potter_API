package com.kenstarry.harrypotter.feature_home.presentation.components

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.harrypotter.R
import com.kenstarry.harrypotter.core.domain.model.CharacterModel
import com.kenstarry.harrypotter.core.presentation.components.CoilImage
import com.kenstarry.harrypotter.core.presentation.components.Lottie
import com.kenstarry.harrypotter.core.presentation.components.PillBtn
import com.kenstarry.harrypotter.core.presentation.components.WizardImage
import com.kenstarry.harrypotter.feature_home.presentation.model.DetailModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailBottomSheet(
    character: CharacterModel,
    onBackPressed: () -> Unit
) {

    val context = LocalContext.current

    val aliasListState = rememberLazyStaggeredGridState()
    val staggeredListState = rememberLazyStaggeredGridState()

    //  details  list
    val characterDetails = remember {
        mutableListOf(
            DetailModel("eye color", character.eyeColour, Icons.Outlined.Visibility),
            DetailModel("ancestry", character.ancestry, Icons.Outlined.Timeline),
            DetailModel("D.O.B", character.dateOfBirth, Icons.Outlined.CalendarMonth),
            DetailModel("gender", character.gender, Icons.Outlined.Male),
            DetailModel("patronous", character.patronus, Icons.Outlined.Male),
            DetailModel("species", character.species, Icons.Outlined.Animation),
            DetailModel("year of Birth", character.yearOfBirth.toString(), Icons.Outlined.CalendarToday),
            DetailModel("actor", character.actor, Icons.Outlined.Movie),
            DetailModel("alive", character.alive.toString(), Icons.Outlined.HealthAndSafety),
        )
    }

    Scaffold(
        topBar = {
            DetailsTopBar(
                onBackPressed = onBackPressed
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                //  image section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //  if the character is a wizard, show a star icon
                        if (character.wizard) {

                            //  wizard image
                            WizardImage(
                                context = context,
                                uri = character.image.toUri()
                            )

                        } else {
                            //  image
                            CoilImage(
                                context = context,
                                imageUri = character.image.toUri(),
                                placeholder = R.drawable.profile,
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
                            verticalPadding = 8.dp,
                            onClick = {}
                        )
                    }

                }

                //  show aliases if there is a value in aliases
                if (character.alternate_names.isNotEmpty()) {

                    Spacer(modifier = Modifier.height(8.dp))

                    //  aliases
                    LazyHorizontalStaggeredGrid(
                        rows = StaggeredGridCells.Fixed(1),
                        content = {
                            items(character.alternate_names) { name ->
                                PillBtn(
                                    title = name,
                                    textColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f),
                                    containerColor = MaterialTheme.colorScheme.onSecondary,
                                    horizontalPadding = 16.dp,
                                    verticalPadding = 16.dp,
                                    onClick = {}
                                )

                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        },
                        state = aliasListState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }

                //  wand section
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(MaterialTheme.colorScheme.onSecondary),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Lottie(
                        rawFile = R.raw.magic_wand,
                        isPlaying = true,
                        iterations = Int.MAX_VALUE,
                        modifier = Modifier
                            .size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    //  name
                    Text(
                        text = "Wand",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //  wand details
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        //  core
                        WandCard(
                            title = "Core",
                            icon = Icons.Outlined.Dashboard,
                            descritpion = character.wand.core,
                            modifier = Modifier
                                .size(100.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        //  length
                        WandCard(
                            title = "Length",
                            icon = Icons.Outlined.Timelapse,
                            descritpion = character.wand.length.toString(),
                            modifier = Modifier
                                .size(100.dp)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        //  wood
                        WandCard(
                            title = "Wood",
                            icon = Icons.Outlined.Handyman,
                            descritpion = character.wand.wood,
                            modifier = Modifier
                                .size(100.dp)
                        )

                    }

                }

                //  further details section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Text(
                        text = "Extra Details",
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.8f)
                    )

                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        content = {
                            items(characterDetails) { detail ->
                                if (detail.description.isNotEmpty()) {
                                    DetailCard(detail)
                                }
                            }
                        },
                        state = staggeredListState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    )

                }
            }

        }

    }

}























