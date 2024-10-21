package com.findrepo.utility.extention

import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavType

/**
 * Sets the argument type for a NavArgumentBuilder to be nullable with a default value of null.
 *
 * @param navType The type of the navigation argument.
 */
fun NavArgumentBuilder.nullableArgumentType(navType: NavType<*>) {
    type = navType // Set the type of the argument
    defaultValue = null // Set the default value to null
    nullable = true // Mark the argument as nullable
}