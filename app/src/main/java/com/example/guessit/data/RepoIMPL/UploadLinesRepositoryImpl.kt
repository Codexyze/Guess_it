package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.domain.Repository.UploadLinesRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UploadLinesRepositoryImpl  @Inject constructor(private val authInstance:FirebaseAuth
                                                     ,private val firebaseFirestore: FirebaseFirestore,
                                                     private val firebaseRealtimeDatabase: FirebaseDatabase
): UploadLinesRepository {
    override suspend fun uploadAllPlayersCanvasPoints(
        lineCordinates: Lines,
        roomID: String
    ): Flow<ResultState<String>>  =callbackFlow{
        trySend(ResultState.Loading)
        firebaseFirestore.collection(Constants.ROOM).document(roomID).collection(Constants.CANVASLINES).document(roomID).set(lineCordinates).addOnSuccessListener {
            trySend(ResultState.Success("SucessFully Sent Coordinates"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }

    }

    override suspend fun uploadLineTorealTimeDatabase(
        lines: LiveLine,
        roomID: String
    ): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseRealtimeDatabase.getReference(roomID).setValue(lines).addOnSuccessListener {
            trySend(ResultState.Success("Sucess"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }
    }
}