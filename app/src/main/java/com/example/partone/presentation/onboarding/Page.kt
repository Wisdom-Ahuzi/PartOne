package com.example.partone.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.partone.R

data class Page(
    val title: String,
    val description:String,
    @DrawableRes val image:Int
)

val pages = listOf(
    Page(
        title = "Lorem ipsum is simply dummy",
        description = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliqro",
        image = R.drawable.onboarding1
    ),

    Page(
        title = "Lorem ipsum dolor sit amet",
        description = "Ut enim ad minim veniam, quis nostrud exercitaute irure dolor in reprehenderit in voluptate velcat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        image = R.drawable.onboarding2
    ),

    Page(
        title = "Lorem ipsum dolor sit amet",
        description = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisiate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        image = R.drawable.onboarding3
    )

)

