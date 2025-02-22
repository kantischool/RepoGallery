package com.findrepo.repogallery.api

import android.content.Context
import androidx.room.Room
import com.findrepo.repogallery.data.AppConstant.BASE_URL
import com.findrepo.repogallery.datasource.AppDataSource
import com.findrepo.repogallery.datasource.AppDataSourceImpl
import com.findrepo.repogallery.repository.AppRepository
import com.findrepo.roomdb.RepoDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder().apply {
        addInterceptor(httpLoggingInterceptor)
        readTimeout(60, TimeUnit.SECONDS)
        writeTimeout(60, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideAppDataSource(apiService: ApiService): AppDataSource = AppDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideApiRequest() = ApiRequest()

    @Provides
    @Singleton
    fun provideAppRepository(
        appDataSource: AppDataSource,
        apiRequest: ApiRequest,
    ) = AppRepository(appDataSource, apiRequest)

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context) = Room.databaseBuilder(context.applicationContext,RepoDatabase::class.java, "RepoDatabase").build()
}