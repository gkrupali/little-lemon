package com.example.littlelemon

interface Destinations{
  val route:String
}

object Onboarding:Destinations{
    override val route: String
        get() = "OnboardingScreen"
}

object HomeScreen:Destinations{
    override val route: String
        get() = "HomeScreen"
}

object ProfileScreen:Destinations{
    override val route:String
        get() = "ProfileScreen"
}
