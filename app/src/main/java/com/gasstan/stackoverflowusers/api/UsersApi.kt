package com.gasstan.stackoverflowusers.api

import com.gasstan.stackoverflowusers.api.response.UsersResponse
import retrofit2.http.GET

interface UsersApi {
  @GET("users?page=1&pagesize=20&order=desc&sort=reputation&site=stackoverflow")
  suspend fun getUsers(): UsersResponse

}