package com.example.guessit.di

import com.example.guessit.data.RepoIMPL.CreateRoomRepositoryImpl
import com.example.guessit.data.RepoIMPL.GetPlayersRepositoryImpl
import com.example.guessit.data.RepoIMPL.GetRealTimeLinesRepositoryImpl
import com.example.guessit.data.RepoIMPL.JoinRoomRepositoryimpl
import com.example.guessit.data.RepoIMPL.MessageRepositoryImpl
import com.example.guessit.data.RepoIMPL.TicTacToeInteractionRepoImpl
import com.example.guessit.data.RepoIMPL.TicTacToeRoomCreateRepoImpl
import com.example.guessit.data.RepoIMPL.UploadLinesRepositoryImpl
import com.example.guessit.data.RepoIMPL.UserAuthRepositoryImpl
import com.example.guessit.data.RepoIMPL.WordsRepositoryImpl
import com.example.guessit.domain.Repository.CreateRoomRepository
import com.example.guessit.domain.Repository.GetPlayersRepository
import com.example.guessit.domain.Repository.GetRealTimeLinesRepository
import com.example.guessit.domain.Repository.JoinRoomRepository
import com.example.guessit.domain.Repository.MessagesRepository
import com.example.guessit.domain.Repository.TicTacToeInteractionRepository
import com.example.guessit.domain.Repository.TicTacToeRoomCreateRepository
import com.example.guessit.domain.Repository.UploadLinesRepository
import com.example.guessit.domain.Repository.UserAuthenticationRepository
import com.example.guessit.domain.Repository.WordsRepository
import com.example.guessit.domain.UseCases.CreateRoomFromServerUseCase
import com.example.guessit.domain.UseCases.CreateTicTacToeRoomUseCase
import com.example.guessit.domain.UseCases.GetAllMessageFromRoomUseCase
import com.example.guessit.domain.UseCases.GetAllPlayersFromRoomUseCase
import com.example.guessit.domain.UseCases.GetRealTimeLineUseCase
import com.example.guessit.domain.UseCases.GetTicTacToeDataUseCase
import com.example.guessit.domain.UseCases.GetWordFromServerUseCase
import com.example.guessit.domain.UseCases.JoinRoomWithIDUseCase
import com.example.guessit.domain.UseCases.LoginUserUseCase
import com.example.guessit.domain.UseCases.SendMessageToAllRoomMemberUseCase
import com.example.guessit.domain.UseCases.SignUpUserUseCase
import com.example.guessit.domain.UseCases.UpdateTicTacToeDataUseCase
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
    fun providefirebasefirestore():FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideFirebaseRealTimeDataBase():FirebaseDatabase{
        return Firebase.database
    }

    @Provides
    fun UseCaseAcessObject (): UseCasesAccess{
        return UseCasesAccess(
            createRoomFromServerUseCase = CreateRoomFromServerUseCase(repository = provideCreateRoomInterfaceObject()),
            getAllMessageFromRoomUseCase = GetAllMessageFromRoomUseCase(repository = provideMessageRepositoryInterfaceObject()),
            getAllPlayersFromRoomUseCase = GetAllPlayersFromRoomUseCase(repository = providegetPlayersReoImplObject()),
            getRealTimeLineUseCase = GetRealTimeLineUseCase(repository = provideGetRealTimeLineRepoImpl()),
            getWordFromServerUseCase = GetWordFromServerUseCase(repository = provideWordsRepoImplObject()),
            joinRoomWithIDUseCase = JoinRoomWithIDUseCase(repository = provideJoinRoomInterfaceObject()),
            loginUserUseCase = LoginUserUseCase(repository = provideUserAuthInterfaceInstance()),
            sendMessageToAllRoomMemberUseCase = SendMessageToAllRoomMemberUseCase(repository = provideMessageRepositoryInterfaceObject()),
            uploadAllPlayersCanvasPoints = UploadAllPlayersCanvasPoints(repository =provideUploadLinesRepoImplObject()),
            uploadLineToRealTimeDataBaseUseCase = UploadLineToRealTimeDataBaseUseCase(repository = provideUploadLinesRepoImplObject()),
            signUpUserUseCase = SignUpUserUseCase(repository = provideUserAuthInterfaceInstance()),
            createTicTacToeRoom = CreateTicTacToeRoomUseCase(repository = provideTicTacToeRoomCreateInterfaceObject()),
            getTicTacToeDAtaUsecase = GetTicTacToeDataUseCase(repository =provideTicTacToeInteractionRepositoryObject()),
            updateTicTacToeDataUseCase = UpdateTicTacToeDataUseCase(
                repository = provideTicTacToeInteractionRepositoryObject()
            )
        )
    }

    @Provides
    fun provideUserAuthInterfaceInstance(): UserAuthenticationRepository{
        return UserAuthRepositoryImpl(authInstance = provideAuthInstance(),
            firebaseFirestore = providefirebasefirestore(),
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase())
    }

    fun provideCreateRoomInterfaceObject(): CreateRoomRepository{
        return CreateRoomRepositoryImpl(firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase()
            , firebaseFirestore = providefirebasefirestore()
            , authInstance = provideAuthInstance())
    }
    fun provideJoinRoomInterfaceObject(): JoinRoomRepository{
        return JoinRoomRepositoryimpl(
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase()
            , firebaseFirestore = providefirebasefirestore()
            , authInstance = provideAuthInstance()
        )
    }
    fun provideWordsRepoImplObject(): WordsRepository{
        return WordsRepositoryImpl(
            firebaseFirestore = providefirebasefirestore(),
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase(),
            authInstance = provideAuthInstance()
        )
    }

    fun providegetPlayersReoImplObject(): GetPlayersRepository{
        return GetPlayersRepositoryImpl(
            firebaseFirestore = providefirebasefirestore(),
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase(),
            authInstance = provideAuthInstance()
        )
    }

    fun provideUploadLinesRepoImplObject(): UploadLinesRepository{
        return UploadLinesRepositoryImpl(
            firebaseFirestore = providefirebasefirestore(),
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase(),
            authInstance = provideAuthInstance()
        )
    }
    fun provideGetRealTimeLineRepoImpl(): GetRealTimeLinesRepository{
        return GetRealTimeLinesRepositoryImpl(
            firebaseFirestore = providefirebasefirestore(),
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase(),
            authInstance = provideAuthInstance()
        )
    }
    fun provideMessageRepositoryInterfaceObject(): MessagesRepository{
        return MessageRepositoryImpl(
            firebaseFirestore = providefirebasefirestore(),
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase(),
            authInstance = provideAuthInstance()
        )
    }

    fun provideTicTacToeRoomCreateInterfaceObject(): TicTacToeRoomCreateRepository{
        return TicTacToeRoomCreateRepoImpl(firebaseAuth = provideAuthInstance(),
            firebaseFireStore = providefirebasefirestore())
    }

    fun provideTicTacToeInteractionRepositoryObject(): TicTacToeInteractionRepository{
        return TicTacToeInteractionRepoImpl(
            authInstance = provideAuthInstance(),
            firebaseFirestore = providefirebasefirestore(),
            firebaseRealtimeDatabase = provideFirebaseRealTimeDataBase()
        )
    }

}