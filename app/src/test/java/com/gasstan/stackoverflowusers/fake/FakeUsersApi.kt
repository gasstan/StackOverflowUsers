package com.gasstan.stackoverflowusers.fake

import com.gasstan.stackoverflowusers.api.UsersApi
import com.gasstan.stackoverflowusers.api.dto.BadgeCountsDto
import com.gasstan.stackoverflowusers.api.dto.UserDto
import com.gasstan.stackoverflowusers.api.response.UsersResponse
import java.io.IOException

val fakeUserDtos = listOf(
    UserDto(
        userId = 1,
        displayName = "Jon Skeet",
        reputation = 1400000,
        profileImage = "https://example.com/jon.jpg",
        userType = "registered",
        location = "Reading, UK",
        websiteUrl = "https://csharpindepth.com",
        link = "https://stackoverflow.com/users/22656",
        badgeCounts = BadgeCountsDto(gold = 877, silver = 9202, bronze = 9255),
    ),
    UserDto(
        userId = 2,
        displayName = "Gordon Linoff",
        reputation = 1200000,
        profileImage = null,
        userType = "registered",
        location = null,
        websiteUrl = null,
        link = "https://stackoverflow.com/users/631920",
        badgeCounts = BadgeCountsDto(gold = 500, silver = 5000, bronze = 8000),
    ),
)

class FakeUsersApi(private val shouldSucceed: Boolean = true) : UsersApi {
    override suspend fun getUsers(): UsersResponse {
        if (!shouldSucceed) throw IOException("Network error")
        return UsersResponse(fakeUserDtos)
    }
}
