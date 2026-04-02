package com.gasstan.stackoverflowusers.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.gasstan.stackoverflowusers.icons.Icons
import com.gasstan.stackoverflowusers.icons.Link
import com.gasstan.stackoverflowusers.icons.LocationOn
import com.gasstan.stackoverflowusers.model.BadgeCounts
import com.gasstan.stackoverflowusers.model.User
import com.gasstan.stackoverflowusers.model.UserType
import com.gasstan.stackoverflowusers.ui.theme.BackgroundCard
import com.gasstan.stackoverflowusers.ui.theme.BackgroundFollowing
import com.gasstan.stackoverflowusers.ui.theme.Bronze
import com.gasstan.stackoverflowusers.ui.theme.OrangePrimary

@Composable
fun UserProfileCard(
    user: User,
    onToggleFollow: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(BackgroundCard)
            .padding(16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp),
            ) {
                AsyncImage(
                    model = user.profileImage,
                    contentDescription = "Profile image of ${user.displayName}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(
                    text = user.displayName,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RoleBadge(user.userType)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "ID: ${user.userId}",
                        color = Color.Gray,
                        fontSize = 12.sp,
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    user.badgeCounts.let {
                        Badge(color = Color.Yellow, value = it.gold)
                        Badge(color = Color.LightGray, value = it.silver)
                        Badge(color = Bronze, value = it.bronze)
                    }
                }
            }
            Spacer(Modifier.weight(1f))
            ReputationBlock(value = user.reputation)
        }
        Spacer(modifier = Modifier.height(12.dp))
        UserInfoSection(
            location = user.location,
            website = user.websiteUrl,
        )
        Spacer(modifier = Modifier.height(12.dp))
        FollowButton(isFollowing = user.isFollowed, onClick = onToggleFollow)
    }
}

@Composable
private fun Badge(color: Color, value: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, CircleShape),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "$value", color = Color.LightGray, fontSize = 12.sp)
    }
}

@Composable
private fun RoleBadge(userType: UserType) {
    Box(
        modifier = Modifier
            .background(userType.badgeColor, RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Text(
            text = userType.name.uppercase(),
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

private fun formatReputation(value: Int): String = when {
    value >= 1_000_000 -> "%.1fM".format(value / 1_000_000.0)
    value >= 1_000 -> "${value / 1_000}k"
    else -> value.toString()
}

@Composable
private fun ReputationBlock(value: Int, label: String = "REPUTATION") {
    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = formatReputation(value),
            color = OrangePrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(text = label, color = Color.Gray, fontSize = 10.sp, letterSpacing = 1.2.sp)
    }
}

@Composable
fun UserInfoSection(location: String, website: String) {
    Column {
        HorizontalDivider(Modifier, thickness = 1.dp, color = OrangePrimary)
        Spacer(modifier = Modifier.height(12.dp))
        InfoRow(icon = Icons.LocationOn, text = location)
        Spacer(modifier = Modifier.height(10.dp))
        InfoRow(icon = Icons.Link, text = website.ifBlank { "-" })
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = OrangePrimary,
            modifier = Modifier.size(18.dp),
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = Color.Gray, fontSize = 14.sp)
    }
}

@Composable
fun FollowButton(
    modifier: Modifier = Modifier,
    isFollowing: Boolean,
    onClick: () -> Unit,
) {
    val color = if (isFollowing) OrangePrimary else Color.Gray
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(1.dp, color),
        shape = RectangleShape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isFollowing) BackgroundFollowing else BackgroundCard,
            contentColor = Color.White,
        ),
    ) {
        Text(
            text = if (isFollowing) "FOLLOWING" else "FOLLOW",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                letterSpacing = 2.sp,
                color = color,
            )
        )
    }
}

@Preview
@Composable
private fun UserProfileCardPreview() {
    UserProfileCard(
        user = User(
            badgeCounts = BadgeCounts(bronze = 10, silver = 30, gold = 50),
            reputation = 142000,
            userType = UserType.Moderator,
            userId = 884291,
            location = "San Francisco, CA",
            websiteUrl = "stackoverflow.com",
            link = "stackoverflow.com",
            profileImage = "",
            displayName = "Alex Rivera",
            isFollowed = true,
        ),
        onToggleFollow = {},
    )
}
