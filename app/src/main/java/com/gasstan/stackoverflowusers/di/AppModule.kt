package com.gasstan.stackoverflowusers.di

import android.content.Context
import com.gasstan.stackoverflowusers.api.UsersApi
import com.gasstan.stackoverflowusers.local.FollowDataSource
import com.gasstan.stackoverflowusers.local.FollowLocalDataSource
import com.gasstan.stackoverflowusers.repo.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://api.stackexchange.com/2.2/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUsersApi(): UsersApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFollowDataSource(@ApplicationContext context: Context): FollowDataSource {
        return FollowLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(usersApi: UsersApi, followDataSource: FollowDataSource): UsersRepository {
        return UsersRepository(usersApi, followDataSource)
    }
}
