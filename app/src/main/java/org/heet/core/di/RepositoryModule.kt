package org.heet.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.heet.data.repository.AutoLoginRepositoryImpl
import org.heet.data.repository.SignUpRepositoryImpl
import org.heet.domain.repository.AutoLoginRepository
import org.heet.domain.repository.SignUpRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAutoLoginRepository(
        autoLoginRepositoryImpl: AutoLoginRepositoryImpl
    ): AutoLoginRepository

    @Binds
    @Singleton
    fun bindSignUpRepository(
        singUpRepositoryImpl: SignUpRepositoryImpl
    ): SignUpRepository
}
