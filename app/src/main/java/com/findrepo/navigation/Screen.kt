package com.findrepo.navigation

sealed class Screen(val route: String, val name: String) {
    data object Repository : Screen(route = "repository", name = "Repository")
    data object RepositoryDetail : Screen(route = "repository_detail", name = "RepositoryDetail")
}