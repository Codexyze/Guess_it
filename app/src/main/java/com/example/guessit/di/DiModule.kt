package com.example.guessit.di

import com.example.guessit.data.RepoIMPL.RepositoryImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
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
        return RepositoryImpl( authInstance = authInstance , firebaseFirestore = providefirebasefirestore())
    }

    @Provides
    fun providefirebasefirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }




}