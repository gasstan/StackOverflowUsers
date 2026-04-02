package com.gasstan.stackoverflowusers.feature.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gasstan.stackoverflowusers.model.ApiResult
import com.gasstan.stackoverflowusers.model.User
import com.gasstan.stackoverflowusers.repo.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<User>>> = _uiState.asStateFlow()

    private var loadJob: Job? = null

    init {
        loadUsers()
    }

    fun retry() = loadUsers()

    fun toggleFollow(userId: Int) {
        val current = _uiState.value as? UiState.Success ?: return
        usersRepository.toggleFollow(userId)
        _uiState.value = UiState.Success(
            current.data.map { if (it.userId == userId) it.copy(isFollowed = !it.isFollowed) else it }
        )
    }

    private fun loadUsers() {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            usersRepository.getUsers().collect { result ->
                _uiState.value = result.toUiState()
            }
        }
    }

    private fun ApiResult<List<User>>.toUiState(): UiState<List<User>> = when (this) {
        is ApiResult.Success -> UiState.Success(data)
        is ApiResult.Error -> UiState.Error(throwable.message ?: "Unknown error")
        is ApiResult.Loading -> UiState.Loading
    }
}

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
