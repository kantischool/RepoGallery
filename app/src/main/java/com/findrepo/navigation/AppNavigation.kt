package com.findrepo.navigation

import androidx.navigation.NavController
import com.findrepo.model.Repository
import com.findrepo.navigation.NavGraphArgument.HTML_DATA
import com.findrepo.navigation.NavGraphArgument.REPOSITORY
import com.findrepo.navigation.NavGraphArgument.REPO_NAME
import com.findrepo.utility.util.parseJSONToString

fun NavController.navigateToRepoDetail(repo: Repository) {
    val hashMap: HashMap<String, Any?> = hashMapOf(
        REPOSITORY to repo.parseJSONToString(Repository::class.java)
    )
    navigate(Screen.RepositoryDetail.withOptionalArgs(hashMap))
}

fun NavController.navigateToWebScreen(url: String, name: String) {
    val hashMap: HashMap<String, Any?> = hashMapOf(
        HTML_DATA to url,
        REPO_NAME to name
    )
    navigate(Screen.WebScreen.withOptionalArgs(hashMap))
}