package com.gasstan.stackoverflowusers.fake

import com.gasstan.stackoverflowusers.local.FollowDataSource

class FakeFollowDataSource : FollowDataSource {

    private val followed = mutableSetOf<Int>()

    override fun getFollowedUserIds(): Set<Int> = followed.toSet()

    override fun followUser(userId: Int) {
        followed.add(userId)
    }

    override fun unfollowUser(userId: Int) {
        followed.remove(userId)
    }

    override fun isFollowed(userId: Int): Boolean = userId in followed
}
