package com.gasstan.stackoverflowusers.model

import androidx.compose.ui.graphics.Color

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

enum class UserType(val badgeColor: Color) {
  Unregistered(badgeColor = Color(0xFF72787E)),
  Registered(badgeColor = Color(0xFF61F176)),
  Moderator(badgeColor = Color(0xFFFF8A00)),
  TeamAdmin(badgeColor = Color(0xFFFF6600)),
  DoesNotExist(badgeColor = Color(0xFF72787E)),
  Unknown(badgeColor = Color(0xFF72787E))
}