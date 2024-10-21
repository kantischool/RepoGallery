package com.findrepo.navigation

import androidx.navigation.NavController
import com.findrepo.model.Repository
import com.findrepo.navigation.NavGraphArgument.REPOSITORY
import com.findrepo.utility.util.parseJSONToString

fun NavController.navigateToRepoDetail(repo: Repository) {
    val hashMap : HashMap<String, Any?> = hashMapOf(
        REPOSITORY to repo.parseJSONToString(Repository::class.java)
    )
    navigate(Screen.RepositoryDetail.withOptionalArgs(hashMap))
}