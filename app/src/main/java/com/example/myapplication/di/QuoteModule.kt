package com.example.myapplication.di

import com.example.myapplication.repository.QuoteRepository
import com.example.myapplication.repository.QuoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class QuoteModule {

    @Binds
    abstract fun bindQuoteRepository(
        quoteRepositoryImpl: QuoteRepositoryImpl
    ): QuoteRepository

}