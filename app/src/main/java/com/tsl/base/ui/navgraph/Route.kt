package com.tsl.base.ui.navgraph

/**
 * @author md-azizul-islam
 * Created 12/23/24 at 4:10 PM
 */
sealed class Route(val path: String) {
    data object OnboardingScreen : Route(path = "onboardingScreen")
    data object LoginScreen : Route(path = "loginScreen")
    data object DashBoardScreen : Route(path = "dashBoardScreen")
}

sealed class Navigation(val nav: String) {
    data object OnboardingNav : Navigation(nav = "OnboardingNav")
    data object LoginNav : Navigation(nav = "LoginNav")
    data object DashboardNav : Navigation(nav = "DashBoardNav")
}