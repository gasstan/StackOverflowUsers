package com.gasstan.stackoverflowusers.feature.homeScreen

import com.gasstan.stackoverflowusers.fake.FakeFollowDataSource
import com.gasstan.stackoverflowusers.fake.FakeUsersApi
import com.gasstan.stackoverflowusers.repo.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var followDataSource: FakeFollowDataSource

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        followDataSource = FakeFollowDataSource()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Loading`() {
        val viewModel = HomeScreenViewModel(UsersRepository(FakeUsersApi(), followDataSource))
        assertEquals(UiState.Loading, viewModel.uiState.value)
    }

    @Test
    fun `uiState becomes Success after successful fetch`() = runTest {
        val viewModel = HomeScreenViewModel(UsersRepository(FakeUsersApi(shouldSucceed = true), followDataSource))
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is UiState.Success)
    }

    @Test
    fun `uiState becomes Error after failed fetch`() = runTest {
        val viewModel = HomeScreenViewModel(UsersRepository(FakeUsersApi(shouldSucceed = false), followDataSource))
        advanceUntilIdle()

        assertTrue(viewModel.uiState.value is UiState.Error)
    }


    @Test
    fun `toggleFollow marks user as followed`() = runTest {
        val viewModel = HomeScreenViewModel(UsersRepository(FakeUsersApi(shouldSucceed = true), followDataSource))
        advanceUntilIdle()

        val userId = (viewModel.uiState.value as UiState.Success).data.first().userId
        viewModel.toggleFollow(userId)

        val state = viewModel.uiState.value as UiState.Success
        assertTrue(state.data.first { it.userId == userId }.isFollowed)
    }
}
