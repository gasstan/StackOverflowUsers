package com.gasstan.stackoverflowusers.local

import android.content.Context
import androidx.core.content.edit

private const val PREFS_NAME = "follow_prefs"
private const val KEY_FOLLOWED_IDS = "followed_user_ids"

class FollowLocalDataSource(context: Context) : FollowDataSource {

  private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

  override fun getFollowedUserIds(): Set<Int> {
    return prefs.getStringSet(KEY_FOLLOWED_IDS, emptySet())
      ?.mapNotNull { it.toIntOrNull() }
      ?.toSet() ?: emptySet()
  }

  override fun followUser(userId: Int) {
    val current = HashSet(prefs.getStringSet(KEY_FOLLOWED_IDS, emptySet()) ?: emptySet())
    current.add(userId.toString())
    prefs.edit { putStringSet(KEY_FOLLOWED_IDS, current) }
  }

  override fun unfollowUser(userId: Int) {
    val current = HashSet(prefs.getStringSet(KEY_FOLLOWED_IDS, emptySet()) ?: emptySet())
    current.remove(userId.toString())
    prefs.edit { putStringSet(KEY_FOLLOWED_IDS, current) }
  }

  override fun isFollowed(userId: Int): Boolean {
    return prefs.getStringSet(KEY_FOLLOWED_IDS, emptySet())
      ?.contains(userId.toString()) ?: false
  }
}
