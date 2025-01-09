package com.tsl.base.ui.onboarding

import androidx.annotation.DrawableRes
import com.tsl.base.R


data class OnboardingItem(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    OnboardingItem(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding1
    ),
    OnboardingItem(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding2
    ),
    OnboardingItem(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.onboarding3
    )
)