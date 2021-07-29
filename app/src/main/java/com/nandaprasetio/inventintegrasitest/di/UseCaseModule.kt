package com.nandaprasetio.inventintegrasitest.di

import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.DefaultItemRepository
import com.nandaprasetio.inventintegrasitest.data.repository.itemrepository.ItemRepository
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.DefaultUserRepository
import com.nandaprasetio.inventintegrasitest.data.repository.userrepository.UserRepository
import com.nandaprasetio.inventintegrasitest.domain.usecase.getcity.DefaultGetCityUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.getcity.GetCityUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.getprovince.DefaultGetProvinceUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.getprovince.GetProvinceUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.loginasemployeeusecase.DefaultLoginAsEmployeeUseCase
import com.nandaprasetio.inventintegrasitest.domain.usecase.loginasemployeeusecase.LoginAsEmployeeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {
    @Binds
    @ViewModelScoped
    abstract fun bindDefaultGetCityUseCase(defaultGetCityUseCase: DefaultGetCityUseCase): GetCityUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDefaultGetProvinceUseCase(defaultGetProvinceUseCase: DefaultGetProvinceUseCase): GetProvinceUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindLoginAsEmployeeUseCase(defaultLoginAsEmployeeUseCase: DefaultLoginAsEmployeeUseCase): LoginAsEmployeeUseCase
}