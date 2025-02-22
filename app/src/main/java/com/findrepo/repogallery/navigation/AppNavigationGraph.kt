package com.findrepo.repogallery.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.findrepo.repogallery.model.item.Repository
import com.findrepo.repogallery.navigation.NavGraphArgument.HTML_DATA
import com.findrepo.repogallery.navigation.NavGraphArgument.REPOSITORY
import com.findrepo.repogallery.navigation.NavGraphArgument.REPO_NAME
import com.findrepo.repogallery.ui.screen.RepositoryDetailScreen
import com.findrepo.repogallery.ui.screen.RepositoryScreen
import com.findrepo.repogallery.ui.screen.WebViewScreen
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
                repo = { repo },
                onBack = { navController.navigateUp() },
                onNavigateToWebScreen = {url, name ->
                    navController.navigateToWebScreen(url, name)
                }
            )
        }

        composable(
            route = Screen.WebScreen.buildOptionalArgsRoute(HTML_DATA, REPO_NAME),
            arguments = listOf(navArgument(HTML_DATA) { nullableArgumentType(NavType.StringType) },
                navArgument(REPO_NAME) { nullableArgumentType(NavType.StringType) }
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString(HTML_DATA)
            val repoName = backStackEntry.arguments?.getString(REPO_NAME)
            WebViewScreen(
                url = { url.toString() },
                repoName = { repoName.toString() },
                onBack = { navController.navigateUp() }
            )
        }
    }
}