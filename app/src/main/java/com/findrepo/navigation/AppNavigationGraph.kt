package com.findrepo.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.findrepo.repogallery.ui.screen.RepositoryScreen
import com.findrepo.utility.util.fadeInTransition
import com.findrepo.utility.util.fadeOutTransition
import com.findrepo.utility.util.slideInHorizontallyTransition
import com.findrepo.utility.util.slideOutHorizontallyTransition

const val NAVIGATION_HOST_ROUTE = "navigation_host_route"

@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Repository.route,
        route = NAVIGATION_HOST_ROUTE,
        enterTransition = { slideInHorizontallyTransition },
        exitTransition = { fadeOutTransition },
        popEnterTransition = { fadeInTransition },
        popExitTransition = { slideOutHorizontallyTransition },
    ){
        composable(route = Screen.Repository.route){
            RepositoryScreen()
        }
    }
}