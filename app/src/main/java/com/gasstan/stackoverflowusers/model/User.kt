package com.gasstan.stackoverflowusers.model

import androidx.compose.ui.graphics.Color
import com.gasstan.stackoverflowusers.ui.theme.BadgeNeutral
import com.gasstan.stackoverflowusers.ui.theme.BadgeRegistered
import com.gasstan.stackoverflowusers.ui.theme.BadgeTeamAdmin
import com.gasstan.stackoverflowusers.ui.theme.OrangePrimary

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
  Unregistered(badgeColor = BadgeNeutral),
  Registered(badgeColor = BadgeRegistered),
  Moderator(badgeColor = OrangePrimary),
  TeamAdmin(badgeColor = BadgeTeamAdmin),
  DoesNotExist(badgeColor = BadgeNeutral),
  Unknown(badgeColor = BadgeNeutral),
}
