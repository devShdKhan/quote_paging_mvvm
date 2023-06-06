package com.example.myapplication.di

import com.example.myapplication.repository.pagingquote.PagingQuoteRepository
import com.example.myapplication.repository.pagingquote.PagingQuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PagingQuoteModule {

    @Binds
    abstract fun bindPagingQuoteRepository(
        pagingQuoteRepositoryImpl: PagingQuoteRepositoryImpl
    ): PagingQuoteRepository

}