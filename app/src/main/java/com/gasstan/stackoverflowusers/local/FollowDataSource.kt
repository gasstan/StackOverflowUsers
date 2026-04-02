package com.gasstan.stackoverflowusers.local

interface FollowDataSource {
  fun getFollowedUserIds(): Set<Int>

  fun followUser(userId: Int)

  fun unfollowUser(userId: Int)

  fun isFollowed(userId: Int): Boolean
}