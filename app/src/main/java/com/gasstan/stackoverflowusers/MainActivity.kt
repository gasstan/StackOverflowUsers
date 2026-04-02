package com.gasstan.stackoverflowusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.gasstan.stackoverflowusers.feature.homeScreen.HomeScreen
import com.gasstan.stackoverflowusers.feature.homeScreen.HomeScreenViewModel
import com.gasstan.stackoverflowusers.ui.theme.StackOverflowUsersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private val homeViewModel: HomeScreenViewModel by viewModels()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      StackOverflowUsersTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          Box(modifier = Modifier.padding(innerPadding)) {
            HomeScreen(homeViewModel)
          }
        }
      }
    }
  }
}