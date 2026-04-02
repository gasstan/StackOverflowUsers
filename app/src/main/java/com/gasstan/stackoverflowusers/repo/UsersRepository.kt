package com.gasstan.stackoverflowusers.repo

import com.gasstan.stackoverflowusers.api.UsersApi
import com.gasstan.stackoverflowusers.api.dto.BadgeCountsDto
import com.gasstan.stackoverflowusers.api.dto.UserDto
import com.gasstan.stackoverflowusers.local.FollowDataSource
import com.gasstan.stackoverflowusers.model.ApiResult
import com.gasstan.stackoverflowusers.model.BadgeCounts
import com.gasstan.stackoverflowusers.model.User
import com.gasstan.stackoverflowusers.model.UserType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepository(
    private val usersApi: UsersApi,
    private val followDataSource: FollowDataSource,
) {

    fun getUsers(): Flow<ApiResult<List<User>>> = flow {
        emit(ApiResult.Loading)
        try {
            val followedIds = followDataSource.getFollowedUserIds()
            val response = usersApi.getUsers()
            emit(ApiResult.Success(response.items.map { it.toUser(followedIds) }))
        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }

    fun toggleFollow(userId: Int) {
        if (followDataSource.isFollowed(userId)) {
            followDataSource.unfollowUser(userId)
        } else {
            followDataSource.followUser(userId)
        }
    }
}

private fun UserDto.toUser(followedIds: Set<Int>) = User(
    badgeCounts = badgeCounts.toBadgeCount(),
    reputation = reputation ?: 0,
    userType = userType.toUserType(),
    userId = userId ?: 0,
    location = location ?: "Unknown",
    websiteUrl = websiteUrl ?: "",
    link = link ?: "",
    profileImage = profileImage ?: "",
    displayName = displayName ?: "No name",
    isFollowed = userId != null && userId in followedIds,
)

private fun BadgeCountsDto?.toBadgeCount(): BadgeCounts {
    if (this == null) return BadgeCounts(0, 0, 0)
    return BadgeCounts(
        bronze = bronze ?: 0,
        silver = silver ?: 0,
        gold = gold ?: 0,
    )
}

private fun String?.toUserType() =
    when (this?.lowercase()) {
        "unregistered" -> UserType.Unregistered
        "registered" -> UserType.Registered
        "moderator" -> UserType.Moderator
        "team_admin" -> UserType.TeamAdmin
        "does_not_exist" -> UserType.DoesNotExist
        else -> UserType.Unknown
    }
