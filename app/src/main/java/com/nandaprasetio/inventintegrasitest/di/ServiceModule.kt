package com.nandaprasetio.inventintegrasitest.di

import com.nandaprasetio.inventintegrasitest.data.service.ItemService
import com.nandaprasetio.inventintegrasitest.data.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {
    @Provides
    @Singleton
    fun provideItemService(retrofit: Retrofit): ItemService = retrofit.create(ItemService::class.java)

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)
}