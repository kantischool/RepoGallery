package com.findrepo.navigation

sealed class Screen(val route: String, val name: String) {
    data object Repository : Screen(route = "repository", name = "Repository")

    data object RepositoryDetail : Screen(route = "repository_detail", name = "RepositoryDetail")

    data object WebScreen : Screen(route = "web_screen", name = "WebScreen")

    fun withOptionalArgs(hashMap: HashMap<String, Any?>) = buildString {
        append(route.plus("?"))
        hashMap.entries.forEachIndexed { index, entry ->
            if (index == hashMap.size - 1) {
                append("${entry.key}=${entry.value}")
            } else {
                append("${entry.key}=${entry.value}&")
            }
        }
    }

    fun buildOptionalArgsRoute(vararg args: Any) = buildString {
        append(route.plus("?"))
        args.forEachIndexed { index, any ->
            if (index == args.size - 1) {
                append("$any={$any}")
            } else {
                append("$any={$any}&")
            }
        }
    }
}