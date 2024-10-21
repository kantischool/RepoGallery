package com.findrepo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.findrepo.model.Repository
import com.findrepo.navigation.NavGraphArgument.REPOSITORY
import com.findrepo.repogallery.ui.screen.RepositoryDetailScreen
import com.findrepo.repogallery.ui.screen.RepositoryScreen
import com.findrepo.utility.extention.nullableArgumentType
import com.findrepo.utility.util.fadeInTransition
import com.findrepo.utility.util.fadeOutTransition
import com.findrepo.utility.util.parseStringToJSON
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
    ) {
        composable(route = Screen.Repository.route) {
            RepositoryScreen(
                onNavigateToRepoDetail = {
                    navController.navigateToRepoDetail(it)
                }
            )
        }

        composable(
            route = Screen.RepositoryDetail.buildOptionalArgsRoute(REPOSITORY),
            arguments = listOf(navArgument(REPOSITORY) { nullableArgumentType(NavType.StringType) })
        ) { backStackEntry ->
            val repo = backStackEntry.arguments?.getString(REPOSITORY)
                .parseStringToJSON(Repository::class.java)

            RepositoryDetailScreen(
                repo = { repo }
            )
        }
    }
}