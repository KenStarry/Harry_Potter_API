package com.example.harrypotter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.harrypotter.feature_home.presentation.HomeScreen
import com.example.harrypotter.navigation.graphs.RootNavGraph
import com.example.harrypotter.ui.theme.HarryPotterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarryPotterTheme {
                RootNavGraph(navHostController = rememberNavController())
            }
        }
    }
}
