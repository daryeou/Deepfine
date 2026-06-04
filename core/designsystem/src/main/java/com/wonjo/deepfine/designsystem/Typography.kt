package com.wonjo.deepfine.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val AppleSDGothicNeo = FontFamily(
    Font(R.font.applesdgothicneo_thin, FontWeight.Thin),
    Font(R.font.applesdgothicneo_extralight, FontWeight.ExtraLight),
    Font(R.font.applesdgothicneo_light, FontWeight.Light),
    Font(R.font.applesdgothicneo_regular, FontWeight.Normal),
    Font(R.font.applesdgothicneo_medium, FontWeight.Medium),
    Font(R.font.applesdgothicneo_semibold, FontWeight.SemiBold),
    Font(R.font.applesdgothicneo_bold, FontWeight.Bold),
    Font(R.font.applesdgothicneo_extrabold, FontWeight.ExtraBold),
    Font(R.font.applesdgothicneo_black, FontWeight.Black),
)

val AppTypography = Typography(
    headlineLarge = TextStyle(fontFamily = AppleSDGothicNeo),
    titleLarge = TextStyle(fontFamily = AppleSDGothicNeo),
    bodyLarge = TextStyle(fontFamily = AppleSDGothicNeo),
    labelMedium = TextStyle(fontFamily = AppleSDGothicNeo),
    labelLarge = TextStyle(fontFamily = AppleSDGothicNeo),
)
