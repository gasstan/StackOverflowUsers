package com.gasstan.stackoverflowusers.api.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
  @SerializedName("badge_counts")
  val badgeCounts: BadgeCountsDto?,
  @SerializedName("reputation")
  val reputation: Int?,
  @SerializedName("user_type")
  val userType: String?,
  @SerializedName("user_id")
  val userId: Int?,
  @SerializedName("location")
  val location: String?,
  @SerializedName("website_url")
  val websiteUrl: String?,
  @SerializedName("link")
  val link: String?,
  @SerializedName("profile_image")
  val profileImage: String?,
  @SerializedName("display_name")
  val displayName: String?,
)

data class BadgeCountsDto(
  val bronze: Int?,
  val silver: Int?,
  val gold: Int?,
)
