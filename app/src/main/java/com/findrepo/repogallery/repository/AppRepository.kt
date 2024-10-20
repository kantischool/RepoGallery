package com.findrepo.repogallery.repository

import com.findrepo.api.ApiRequest
import com.findrepo.datasource.AppDataSource
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDataSource: AppDataSource,
    private val api: ApiRequest,
) {
}