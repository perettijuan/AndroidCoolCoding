package com.jpp.usercentricschallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.jpp.usercentricschallenge.ui.home.HomeScreen
import com.jpp.usercentricschallenge.ui.home.HomeScreenViewModel
import com.jpp.usercentricschallenge.ui.theme.UsercentricsChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsercentricsChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val viewModel : HomeScreenViewModel by viewModels()
                    HomeScreen(viewModel)
                }
            }
        }
    }
}
