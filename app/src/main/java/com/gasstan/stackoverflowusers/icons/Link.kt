package com.gasstan.stackoverflowusers.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Link: ImageVector
  get() {
    if (_Link != null) {
      return _Link!!
    }
    _Link = ImageVector.Builder(
      name = "Link",
      defaultWidth = 24.dp,
      defaultHeight = 24.dp,
      viewportWidth = 960f,
      viewportHeight = 960f
    ).apply {
      path(fill = SolidColor(Color.Black)) {
        moveTo(440f, 680f)
        lineTo(280f, 680f)
        quadToRelative(-83f, 0f, -141.5f, -58.5f)
        reflectiveQuadTo(80f, 480f)
        quadToRelative(0f, -83f, 58.5f, -141.5f)
        reflectiveQuadTo(280f, 280f)
        horizontalLineToRelative(160f)
        verticalLineToRelative(80f)
        lineTo(280f, 360f)
        quadToRelative(-50f, 0f, -85f, 35f)
        reflectiveQuadToRelative(-35f, 85f)
        quadToRelative(0f, 50f, 35f, 85f)
        reflectiveQuadToRelative(85f, 35f)
        horizontalLineToRelative(160f)
        verticalLineToRelative(80f)
        close()
        moveTo(320f, 520f)
        verticalLineToRelative(-80f)
        horizontalLineToRelative(320f)
        verticalLineToRelative(80f)
        lineTo(320f, 520f)
        close()
        moveTo(520f, 680f)
        verticalLineToRelative(-80f)
        horizontalLineToRelative(160f)
        quadToRelative(50f, 0f, 85f, -35f)
        reflectiveQuadToRelative(35f, -85f)
        quadToRelative(0f, -50f, -35f, -85f)
        reflectiveQuadToRelative(-85f, -35f)
        lineTo(520f, 360f)
        verticalLineToRelative(-80f)
        horizontalLineToRelative(160f)
        quadToRelative(83f, 0f, 141.5f, 58.5f)
        reflectiveQuadTo(880f, 480f)
        quadToRelative(0f, 83f, -58.5f, 141.5f)
        reflectiveQuadTo(680f, 680f)
        lineTo(520f, 680f)
        close()
      }
    }.build()

    return _Link!!
  }

@Suppress("ObjectPropertyName")
private var _Link: ImageVector? = null
