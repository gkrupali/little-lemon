package com.example.littlelemon.view

 import android.content.Context
 import androidx.compose.runtime.Composable
 import androidx.compose.ui.platform.LocalContext
 import androidx.navigation.NavHostController
 import androidx.navigation.compose.NavHost
 import androidx.navigation.compose.composable
 import com.example.littlelemon.HomeScreen
 import com.example.littlelemon.Onboarding
 import com.example.littlelemon.ProfileScreen

@Composable
fun Navigation(navController: NavHostController) {

    val sharedPref = LocalContext.current.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    val startDestination = if(sharedPref.getString("FirstName", "").toString().isBlank()){
        Onboarding.route
    }else{
        HomeScreen.route
    }

    val navHost = NavHost(navController = navController, startDestination = startDestination){
        composable(HomeScreen.route){
            HomeScreen(navController = navController)
        }
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(ProfileScreen.route){
            ProfileScreen(navController)
        }
    }
}