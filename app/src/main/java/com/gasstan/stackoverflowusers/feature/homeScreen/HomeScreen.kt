package com.gasstan.stackoverflowusers.feature.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gasstan.stackoverflowusers.model.User
import com.gasstan.stackoverflowusers.ui.components.UserProfileCard
import com.gasstan.stackoverflowusers.ui.theme.BackgroundScreen
import com.gasstan.stackoverflowusers.ui.theme.OrangePrimary

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel) {
    val uiState by homeScreenViewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is UiState.Error -> ErrorComposable(state.message, homeScreenViewModel::retry)
        UiState.Loading -> LoadingComposable()
        is UiState.Success -> ContentComposable(state.data, homeScreenViewModel::toggleFollow)
    }
}

@Composable
private fun ContentComposable(users: List<User>, onToggleFollow: (Int) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundScreen)
            .padding(8.dp)
    ) {
        items(users, key = { it.userId }) { user ->
            UserProfileCard(
                user = user,
                onToggleFollow = { onToggleFollow(user.userId) },
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun LoadingComposable() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundScreen),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = OrangePrimary)
    }
}

@Composable
private fun ErrorComposable(message: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundScreen),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = message,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp),
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
            ) {
                Text("Retry", color = Color.Black)
            }
        }
    }
}
