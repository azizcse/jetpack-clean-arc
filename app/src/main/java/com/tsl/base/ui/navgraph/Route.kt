package com.tsl.base.ui.navgraph

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 4:10 PM
 */
sealed class Route(val path: String) {
    data object OnboardingScreen : Route(path = "onBoardingScreen")
    data object LoginScreen : Route(path = "loginScreen")
    data object DashBoardScreen : Route(path = "onBoardingScreen")
}

sealed class Navigation(val nav: String) {
    data object OnboardingNav : Navigation(nav = "OnboardingNav")
    data object LoginNav : Navigation(nav = "OnboardingNav")
    data object DashboardNav : Navigation(nav = "OnboardingNav")
}