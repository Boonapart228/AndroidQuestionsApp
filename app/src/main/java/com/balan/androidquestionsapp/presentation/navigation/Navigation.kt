package com.balan.androidquestionsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.balan.androidquestionsapp.presentation.authorization_screen.AuthorizationScreen
import com.balan.androidquestionsapp.presentation.authorization_screen.components.AuthorizationViewModel
import com.balan.androidquestionsapp.presentation.main_screen.MainScreen
import com.balan.androidquestionsapp.presentation.registration_screen.RegistrationScreen


@Composable
fun Navigation(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = "AuthorizationScreen"
    ) {
        composable(route = "AuthorizationScreen") {
            val viewModel: AuthorizationViewModel = hiltViewModel()
            AuthorizationScreen(
                viewModel = viewModel,
                navigateSingUp = { navHostController.navigate("RegistrationScreen") },
                navigateSingIn = { navHostController.navigate("MainScreen") })
        }
        composable(route = "RegistrationScreen") {
            RegistrationScreen()
        }
        composable(route = "MainScreen") {
            MainScreen()
        }
    }

}