package com.example.guessit.di

import com.example.guessit.data.RepoIMPL.RepositoryImpl
import com.example.guessit.domain.Repository.Repository
import com.example.guessit.domain.UseCases.CreateRoomFromServerUseCase
import com.example.guessit.domain.UseCases.GetAllMessageFromRoomUseCase
import com.example.guessit.domain.UseCases.GetAllPlayersFromRoomUseCase
import com.example.guessit.domain.UseCases.GetRealTimeLineUseCase
import com.example.guessit.domain.UseCases.GetWordFromServerUseCase
import com.example.guessit.domain.UseCases.JoinRoomWithIDUseCase
import com.example.guessit.domain.UseCases.LoginUserUseCase
import com.example.guessit.domain.UseCases.SendMessageToAllRoomMemberUseCase
import com.example.guessit.domain.UseCases.SignUpUserUseCase
import com.example.guessit.domain.UseCases.UploadAllPlayersCanvasPoints
import com.example.guessit.domain.UseCases.UploadLineToRealTimeDataBaseUseCase
import com.example.guessit.domain.UseCases.UseCasesAccess
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
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
        return RepositoryImpl( authInstance = authInstance , firebaseFirestore = providefirebasefirestore(), firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase())
    }

    @Provides
    fun providefirebasefirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideFirebaseRealTimeDataBase():FirebaseDatabase{
        return Firebase.database
    }

    @Provides
    fun provideRepository(authInstance:FirebaseAuth): Repository{
        return RepositoryImpl( authInstance = authInstance , firebaseFirestore = providefirebasefirestore(), firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase())
    }

    @Provides
    fun UseCaseAcessObject (): UseCasesAccess{
        return UseCasesAccess(
            createRoomFromServerUseCase = CreateRoomFromServerUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            getAllMessageFromRoomUseCase = GetAllMessageFromRoomUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            getAllPlayersFromRoomUseCase = GetAllPlayersFromRoomUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            getRealTimeLineUseCase = GetRealTimeLineUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            getWordFromServerUseCase = GetWordFromServerUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            joinRoomWithIDUseCase = JoinRoomWithIDUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            loginUserUseCase = LoginUserUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            sendMessageToAllRoomMemberUseCase = SendMessageToAllRoomMemberUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            uploadAllPlayersCanvasPoints = UploadAllPlayersCanvasPoints(repository = provideRepository(authInstance = provideAuthInstance())),
            uploadLineToRealTimeDataBaseUseCase = UploadLineToRealTimeDataBaseUseCase(repository = provideRepository(authInstance = provideAuthInstance())),
            signUpUserUseCase = SignUpUserUseCase(repository = provideRepository(authInstance = provideAuthInstance()))
        )
    }


}