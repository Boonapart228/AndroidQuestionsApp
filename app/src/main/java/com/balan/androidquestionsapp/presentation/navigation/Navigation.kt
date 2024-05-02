package com.balan.androidquestionsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.balan.androidquestionsapp.presentation.sign_in.SignInScreen
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInViewModel
import com.balan.androidquestionsapp.presentation.main_screen.MainScreen
import com.balan.androidquestionsapp.presentation.main_screen.components.MainViewModel
import com.balan.androidquestionsapp.presentation.navigation.model.Screens
import com.balan.androidquestionsapp.presentation.result.ResultScreen
import com.balan.androidquestionsapp.presentation.result.components.ResultViewModel
import com.balan.androidquestionsapp.presentation.sign_up.SignUpScreen
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpViewModel
import com.balan.androidquestionsapp.presentation.test_screen.TestScreen
import com.balan.androidquestionsapp.presentation.test_screen.components.TestViewModel


@Composable
fun Navigation(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.SIGN_IN.screen
    ) {
        composable(route = Screens.SIGN_IN.screen) {
            val viewModel: SignInViewModel = hiltViewModel()
            SignInScreen(
                viewModel = viewModel,
                navigateSignUp = { navHostController.navigate(Screens.SIGN_UP.screen) },
                navigateSignIn = { navHostController.navigate(Screens.MAIN.screen) })
        }
        composable(route = Screens.SIGN_UP.screen) {
            val viewModel: SignUpViewModel = hiltViewModel()
            SignUpScreen(
                viewModel = viewModel,
                navigateSignIn = { navHostController.navigate(Screens.SIGN_IN.screen) }
            )
        }
        composable(route = Screens.MAIN.screen) {
            val viewModel: MainViewModel = hiltViewModel()
            MainScreen(
                viewModel = viewModel,
                navigationTest = { navHostController.navigate(Screens.TEST.screen) },
                navigationSignIn = { navHostController.navigate(Screens.SIGN_IN.screen) }
            )
        }
        composable(route = Screens.TEST.screen) {
            val viewModel: TestViewModel = hiltViewModel()
            TestScreen(
                viewModel = viewModel,
                navigateMain = { navHostController.navigate(Screens.MAIN.screen) },
                navigateResult = { navHostController.navigate(Screens.RESULT.screen) }
            )
        }
        composable(route = Screens.RESULT.screen) {
            val viewModel: ResultViewModel = hiltViewModel()
            ResultScreen(
                viewModel = viewModel,
                navigateMain = { navHostController.navigate(Screens.MAIN.screen) },
            )
        }
    }

}