package team.nexters.semonemo.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import team.nexters.semonemo.R

val Pretendard = FontFamily(
    Font(R.font.pretendard_bold, FontWeight.Bold),
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular)
)

val Heading1Bold = TextStyle(
    fontFamily = Pretendard,
    fontSize = 32.sp,
    fontWeight = FontWeight.Bold,
    lineHeight = 44.8.sp,
)

val Heading1SemiBold = TextStyle(
    fontFamily = Pretendard,
    fontSize = 32.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 44.8.sp,
)

val Heading2SemiBold = TextStyle(
    fontFamily = Pretendard,
    fontSize = 24.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 33.6.sp,
)

val Heading2Medium = TextStyle(
    fontFamily = Pretendard,
    fontSize = 24.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 33.6.sp,
)

val Heading3SemiBold = TextStyle(
    fontFamily = Pretendard,
    fontSize = 18.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 25.2.sp,
)

val Heading4SemiBold = TextStyle(
    fontFamily = Pretendard,
    fontSize = 16.sp,
    fontWeight = FontWeight.SemiBold,
    lineHeight = 22.4.sp,
    letterSpacing = (-0.02).sp
)

val Paragraph1Medium = TextStyle(
    fontFamily = Pretendard,
    fontSize = 16.sp,
    fontWeight = FontWeight.Medium,
    lineHeight = 22.4.sp,
    letterSpacing = (-0.02).sp
)

val Paragraph1Regular = TextStyle(
    fontFamily = Pretendard,
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 22.4.sp,
    letterSpacing = (-0.02).sp
)

val Paragraph2Regular = TextStyle(
    fontFamily = Pretendard,
    fontSize = 14.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 19.6.sp,
    letterSpacing = (-0.02).sp
)

val Caption1Regular = TextStyle(
    fontFamily = Pretendard,
    fontSize = 12.sp,
    fontWeight = FontWeight.Normal,
    lineHeight = 16.8.sp,
    letterSpacing = (-0.02).sp
)

val Typography = Typography(
    headlineLarge = Heading1Bold,
    headlineMedium = Heading1SemiBold,
    headlineSmall = Heading2SemiBold,
    titleLarge = Heading3SemiBold,
    titleSmall = Heading4SemiBold,
    bodyLarge = Paragraph1Medium,
    bodyMedium = Paragraph2Regular,
    labelLarge = Caption1Regular
)


