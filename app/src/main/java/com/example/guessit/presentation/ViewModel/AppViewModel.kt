package com.example.guessit.presentation.ViewModel

import androidx.lifecycle.ViewModel
import com.example.guessit.data.RepoIMPL.RepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor( private val repositoryImpl: RepositoryImpl,private val authInstance:FirebaseAuth):ViewModel () {


}