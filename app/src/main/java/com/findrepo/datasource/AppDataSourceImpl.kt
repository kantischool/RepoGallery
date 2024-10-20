package com.findrepo.datasource

import com.findrepo.api.ApiService
import javax.inject.Inject

class AppDataSourceImpl @Inject constructor(
    apiService: ApiService
) : AppDataSource {
}