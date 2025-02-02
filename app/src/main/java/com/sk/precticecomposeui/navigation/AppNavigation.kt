package com.sk.precticecomposeui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sk.precticecomposeui.screens.HomeScreen
import com.sk.precticecomposeui.screens.LoginScreen
import com.sk.precticecomposeui.screens.SignUpScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") {
            LoginScreen(navController)
        }
        composable("SignUpScreen"){
            SignUpScreen(navController)
        }
        composable("HomeScreen") {
            HomeScreen(navController)
        }

    }

}