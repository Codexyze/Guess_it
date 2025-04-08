package com.example.guessit.data.RepoIMPL

import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.domain.Repository.MessagesRepository
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

class MessageRepositoryImpl @Inject constructor(private val authInstance:FirebaseAuth
                                                ,private val firebaseFirestore: FirebaseFirestore,
                                                private val firebaseRealtimeDatabase: FirebaseDatabase
): MessagesRepository {
    private val currentUserAuth= authInstance.currentUser?.uid?:""
    private val firebaseRealtimeRefrence = firebaseRealtimeDatabase
    private val randomvalue = "1"

    override suspend fun sendMessageToAllRoomMembers(
        roomID: String,
        message: Message
    ): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseRealtimeRefrence.reference.child("${roomID}_$randomvalue").child(Constants.MESSAGES).push().setValue(message).addOnSuccessListener {
            trySend(ResultState.Success("SucessFull"))
        }.addOnFailureListener {
            trySend(ResultState.Error(it.message.toString()))
        }
        awaitClose {
            close()
        }
    }

    override suspend fun getAllMessagesFromRoom(roomID: String): Flow<ResultState<List<Message>>> = callbackFlow {
        trySend(ResultState.Loading)

        val listener = firebaseRealtimeRefrence.reference
            .child("${roomID}_$randomvalue")
            .child(Constants.MESSAGES)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val messages = snapshot.children.mapNotNull { it.getValue(Message::class.java) }
                        trySend(ResultState.Success(messages))
                    } else {
                        trySend(ResultState.Success(emptyList())) // If no messages found, send empty list
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    trySend(ResultState.Error(error.message))
                }
            })

        awaitClose { firebaseRealtimeRefrence.reference.removeEventListener(listener) }
    }


}