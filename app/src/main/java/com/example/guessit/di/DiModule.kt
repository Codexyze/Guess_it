package com.example.guessit.di

import com.example.guessit.data.RepoIMPL.RepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    fun provideAuthInstance():FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    fun provideRepoImplInstnace(authInstance:FirebaseAuth): RepositoryImpl{
        return RepositoryImpl( authInstance = authInstance)
    }


}