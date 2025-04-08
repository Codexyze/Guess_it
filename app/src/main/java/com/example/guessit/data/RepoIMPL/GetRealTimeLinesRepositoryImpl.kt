package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.domain.Repository.GetRealTimeLinesRepository
import com.example.guessit.domain.StateHandeling.ResultState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class GetRealTimeLinesRepositoryImpl  @Inject constructor(private val authInstance:FirebaseAuth
                                                          ,private val firebaseFirestore: FirebaseFirestore,
                                                          private val firebaseRealtimeDatabase: FirebaseDatabase
): GetRealTimeLinesRepository {
    override suspend fun getRealtimeLines(roomID: String): Flow<ResultState<LiveLine>>  = callbackFlow {
        trySend(ResultState.Loading)

        val reference = firebaseRealtimeDatabase.getReference(roomID)

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.getValue(LiveLine::class.java)
                if (data != null) {
                    trySend(ResultState.Success(data))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ResultState.Error(error.message))
            }
        }

        reference.addValueEventListener(listener)

        awaitClose {
            reference.removeEventListener(listener) // Remove listener when not needed
        }
    }

}