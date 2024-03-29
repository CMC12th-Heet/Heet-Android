package org.heet.core.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.heet.data.repository.*
import org.heet.domain.repository.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindAutoLoginRepository(
        autoLoginRepositoryImpl: AutoLoginRepositoryImpl,
    ): AutoLoginRepository

    @Binds
    @Singleton
    fun bindSignUpRepository(
        singUpRepositoryImpl: SignUpRepositoryImpl,
    ): SignUpRepository

    @Binds
    @Singleton
    fun bindCodeRepository(
        codeRepositoryImpl: CodeRepositoryImpl,
    ): CodeRepository

    @Binds
    @Singleton
    fun bindUserInfoRepository(
        userInfoRepositoryImpl: UserInfoRepositoryImpl,
    ): UserInfoRepository

    @Binds
    @Singleton
    fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl,
    ): LoginRepository

    @Binds
    @Singleton
    fun bindCommentRepository(
        commentRepositoryImpl: CommentRepositoryImpl,
    ): CommentRepository

    @Binds
    @Singleton
    fun bindStoreRepository(
        storeRepositoryImpl: StoreRepositoryImpl,
    ): StoreRepository

    @Binds
    @Singleton
    fun bindMyPageRepository(
        myPageRepositoryImpl: MyPageRepositoryImpl,
    ): MyPageRepository

    @Binds
    @Singleton
    fun bindPostRepository(
        postRepositoryImpl: PostRepositoryImpl,
    ): PostRepository

    @Binds
    @Singleton
    fun bindVerifyRepository(
        verifyRepositoryImpl: VerifyRepositoryImpl,
    ): VerifyRepository

    @Binds
    @Singleton
    fun bindAddressRepository(
        addressRepositoryImpl: AddressRepositoryImpl,
    ): AddressRepository

    @Binds
    @Singleton
    fun bindFollowRepository(
        followRepositoryImpl: FollowRepositoryImpl,
    ): FollowRepository

    @Binds
    @Singleton
    fun bindSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl,
    ): SearchRepository

    @Binds
    @Singleton
    fun bindResetRepository(
        resetRepositoryImpl: ResetRepositoryImpl,
    ): ResetRepository

    @Binds
    @Singleton
    fun bindBookmarkRepository(
        bookmarkRepositoryImpl: BookmarkRepositoryImpl,
    ): BookmarkRepository
}
