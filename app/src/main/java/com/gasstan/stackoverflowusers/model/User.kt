package com.gasstan.stackoverflowusers.model

data class User(
  val badgeCounts: BadgeCounts,
  val reputation: Int,
  val userType: UserType,
  val userId: Int,
  val location: String,
  val websiteUrl: String,
  val link: String,
  val profileImage: String,
  val displayName: String,
  val isFollowed: Boolean = false,
)

data class BadgeCounts(
  val bronze: Int,
  val silver: Int,
  val gold: Int,
)

enum class UserType {
  Unregistered,
  Registered,
  Moderator,
  TeamAdmin,
  DoesNotExist,
  Unknown,
}
