package com.nandaprasetio.inventintegrasitest.di

import com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource.DefaultItemDataSource
import com.nandaprasetio.inventintegrasitest.data.datasource.itemdatasource.ItemDataSource
import com.nandaprasetio.inventintegrasitest.data.datasource.userdatasource.DefaultUserDataSource
import com.nandaprasetio.inventintegrasitest.data.datasource.userdatasource.UserDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindDefaultItemDataSource(defaultItemDataSource: DefaultItemDataSource): ItemDataSource

    @Binds
    @Singleton
    abstract fun bindDefaultUserDataSource(defaultUserDataSource: DefaultUserDataSource): UserDataSource
}