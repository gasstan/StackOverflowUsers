package com.gasstan.stackoverflowusers.repo

import com.gasstan.stackoverflowusers.fake.FakeFollowDataSource
import com.gasstan.stackoverflowusers.fake.FakeUsersApi
import com.gasstan.stackoverflowusers.model.ApiResult
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UsersRepositoryTest {

    private lateinit var followDataSource: FakeFollowDataSource
    private lateinit var repository: UsersRepository

    @Before
    fun setUp() {
        followDataSource = FakeFollowDataSource()
    }

    @Test
    fun `getUsers emits Loading then Success when API succeeds`() = runTest {
        repository = UsersRepository(FakeUsersApi(shouldSucceed = true), followDataSource)

        val results = repository.getUsers().toList()

        assertEquals(2, results.size)
        assertTrue(results[0] is ApiResult.Loading)
        assertTrue(results[1] is ApiResult.Success)
    }

    @Test
    fun `getUsers emits Loading then Error when API fails`() = runTest {
        repository = UsersRepository(FakeUsersApi(shouldSucceed = false), followDataSource)

        val results = repository.getUsers().toList()

        assertEquals(2, results.size)
        assertTrue(results[0] is ApiResult.Loading)
        assertTrue(results[1] is ApiResult.Error)
    }

    @Test
    fun `follow a user`() {
        repository = UsersRepository(FakeUsersApi(), followDataSource)

        assertFalse(followDataSource.isFollowed(1))
        repository.toggleFollow(1)
        assertTrue(followDataSource.isFollowed(1))
    }
}
