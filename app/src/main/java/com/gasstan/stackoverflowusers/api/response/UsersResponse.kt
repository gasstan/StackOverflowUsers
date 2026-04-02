package com.gasstan.stackoverflowusers.api.response

import com.gasstan.stackoverflowusers.api.dto.UserDto

data class UsersResponse(
    val items: List<UserDto>
)
