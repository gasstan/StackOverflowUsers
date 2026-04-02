package com.gasstan.stackoverflowusers.model

sealed class ApiResult<out T> {
  data class Success<T>(val data: T) : ApiResult<T>()
  data class Error(val throwable: Throwable) : ApiResult<Nothing>()
  object Loading : ApiResult<Nothing>()
}
