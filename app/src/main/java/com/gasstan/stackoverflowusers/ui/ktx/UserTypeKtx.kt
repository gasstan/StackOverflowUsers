package com.gasstan.stackoverflowusers.ui.ktx

import androidx.compose.ui.graphics.Color
import com.gasstan.stackoverflowusers.model.UserType
import com.gasstan.stackoverflowusers.ui.theme.BadgeNeutral
import com.gasstan.stackoverflowusers.ui.theme.BadgeRegistered
import com.gasstan.stackoverflowusers.ui.theme.BadgeTeamAdmin
import com.gasstan.stackoverflowusers.ui.theme.OrangePrimary

val UserType.badgeColor: Color
  get() =  when(this){
    UserType.Unregistered -> BadgeNeutral
    UserType.Registered ->  BadgeRegistered
    UserType.Moderator ->  OrangePrimary
    UserType.TeamAdmin ->  BadgeTeamAdmin
    UserType.DoesNotExist ->  BadgeNeutral
    UserType.Unknown -> BadgeNeutral
  }