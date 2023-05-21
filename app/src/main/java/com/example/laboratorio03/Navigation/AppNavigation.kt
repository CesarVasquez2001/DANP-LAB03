package com.example.laboratorio03.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.laboratorio03.Screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HOME.route) {
        composable(route = AppScreens.HOME.route) {
            Home(navController)
        }
        composable(route = AppScreens.ADD.route) {
            Add(navController)
        }

        composable(route = AppScreens.EDIT.route) {
            Edit(navController)
        }
        composable(route = AppScreens.DELETE.route) {
            Delete(navController)
        }
        composable(route = AppScreens.LIST.route) {
            List(navController)
        }
        composable(route = AppScreens.EDITFORM.route + "/{text}",
            arguments = listOf(navArgument(name = "text") {
                type = NavType.StringType
            })
        ) {
            EditForm(navController,it.arguments?.getString("text"))
        }


    }
}