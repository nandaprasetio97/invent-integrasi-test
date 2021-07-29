package com.nandaprasetio.inventintegrasitest.di

import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.DefaultItemRepository
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.ItemRepository
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.DefaultUserRepository
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindDefaultItemRepository(defaultItemRepository: DefaultItemRepository): ItemRepository

    @Binds
    @Singleton
    abstract fun bindDefaultUserRepository(defaultUserRepository: DefaultUserRepository): UserRepository
}