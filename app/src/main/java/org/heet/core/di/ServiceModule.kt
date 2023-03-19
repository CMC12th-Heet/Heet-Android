package org.heet.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.heet.data.service.*
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ServiceModule {

    @Provides
    @Singleton
    fun provideSignupService(retrofit: Retrofit): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun provideCommentService(retrofit: Retrofit): CommentService {
        return retrofit.create(CommentService::class.java)
    }

    @Provides
    @Singleton
    fun provideStoreService(retrofit: Retrofit): StoreService {
        return retrofit.create(StoreService::class.java)
    }

    @Provides
    @Singleton
    fun provideMyPageService(retrofit: Retrofit): MyPageService {
        return retrofit.create(MyPageService::class.java)
    }

    @Provides
    @Singleton
    fun providePostService(retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Provides
    @Singleton
    fun provideFollowService(retrofit: Retrofit): FollowService {
        return retrofit.create(FollowService::class.java)
    }
}
