package com.wonjo.deepfine.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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

object AppTextStyles {
    val ScreenTitle = TextStyle(
        fontFamily = AppleSDGothicNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
    )
    val ScreenDescription = TextStyle(
        fontFamily = AppleSDGothicNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
    )
    val TextFieldLabel = TextStyle(
        fontFamily = AppleSDGothicNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp,
    )
    val TextFieldInput = TextStyle(
        fontFamily = AppleSDGothicNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    )
    val TextFieldSupporting = TextStyle(
        fontFamily = AppleSDGothicNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    )
    val Button = TextStyle(
        fontFamily = AppleSDGothicNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
    )
}

val AppTypography = Typography(
    headlineLarge = AppTextStyles.ScreenTitle,
    titleLarge = AppTextStyles.ScreenTitle,
    bodyLarge = AppTextStyles.TextFieldInput,
    labelMedium = AppTextStyles.TextFieldLabel,
    labelLarge = AppTextStyles.Button,
)
