package com.balan.androidquestionsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.balan.androidquestionsapp.presentation.admin.AdminScreen
import com.balan.androidquestionsapp.presentation.admin.components.AdminViewModel
import com.balan.androidquestionsapp.presentation.sign_in.SignInScreen
import com.balan.androidquestionsapp.presentation.sign_in.components.SignInViewModel
import com.balan.androidquestionsapp.presentation.main_screen.MainScreen
import com.balan.androidquestionsapp.presentation.main_screen.components.MainViewModel
import com.balan.androidquestionsapp.presentation.navigation.model.Screens
import com.balan.androidquestionsapp.presentation.result.ResultScreen
import com.balan.androidquestionsapp.presentation.result.components.ResultViewModel
import com.balan.androidquestionsapp.presentation.score.ScoreScreen
import com.balan.androidquestionsapp.presentation.score.components.ScoreViewModel
import com.balan.androidquestionsapp.presentation.sign_up.SignUpScreen
import com.balan.androidquestionsapp.presentation.sign_up.components.SignUpViewModel
import com.balan.androidquestionsapp.presentation.test_screen.TestScreen
import com.balan.androidquestionsapp.presentation.test_screen.components.TestViewModel


@Composable
fun Navigation(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.SIGN_IN.route
    ) {
        composable(route = Screens.SIGN_IN.route) {
            val viewModel: SignInViewModel = hiltViewModel()
            SignInScreen(
                viewModel = viewModel,
                navigateToSignUp = { navHostController.navigate(Screens.SIGN_UP.route) },
                navigateToSignIn = { navHostController.navigate(Screens.MAIN.route) })
        }
        composable(route = Screens.SIGN_UP.route) {
            val viewModel: SignUpViewModel = hiltViewModel()
            SignUpScreen(
                viewModel = viewModel,
                navigateToSignIn = { navHostController.navigate(Screens.SIGN_IN.route) }
            )
        }
        composable(route = Screens.MAIN.route) {
            val viewModel: MainViewModel = hiltViewModel()
            MainScreen(
                viewModel = viewModel,
                navigateToTest = { navHostController.navigate(Screens.TEST.route) },
                navigateToSignIn = { navHostController.navigate(Screens.SIGN_IN.route) },
                navigateToAdmin = { navHostController.navigate(Screens.ADMIN.route) }
            )
        }
        composable(route = Screens.TEST.route) {
            val viewModel: TestViewModel = hiltViewModel()
            TestScreen(
                viewModel = viewModel,
                navigateToMain = { navHostController.navigate(Screens.MAIN.route) },
                navigateToResult = { navHostController.navigate(Screens.RESULT.route) }
            )
        }
        composable(route = Screens.RESULT.route) {
            val viewModel: ResultViewModel = hiltViewModel()
            ResultScreen(
                viewModel = viewModel,
                navigateToMain = { navHostController.navigate(Screens.MAIN.route) },
            )
        }
        composable(route = Screens.ADMIN.route) {
            val viewModel: AdminViewModel = hiltViewModel()
            AdminScreen(
                viewModel = viewModel,
                navigateToMain = { navHostController.navigate(Screens.MAIN.route) },
                navigateToScore = { navHostController.navigate(Screens.SCORE.route) },
            )
        }
        composable(route = Screens.SCORE.route) {
            val viewModel: ScoreViewModel = hiltViewModel()
            ScoreScreen(
                viewModel = viewModel,
                navigateToMain = { navHostController.navigate(Screens.MAIN.route) },
            )
        }
    }

}