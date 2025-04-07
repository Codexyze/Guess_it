package com.example.guessit.data.RepoIMPL

import android.util.Log
import com.example.guessit.data.Constants.Constants
import com.example.guessit.data.MessageDataClasses.Message
import com.example.guessit.data.PainterDataClass.Lines
import com.example.guessit.data.PainterDataClass.LiveLine
import com.example.guessit.data.dataClasses.Player
import com.example.guessit.domain.Repository.Repository
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


class RepositoryImpl @Inject constructor(private val authInstance:FirebaseAuth
,private val firebaseFirestore: FirebaseFirestore,
    private val firebaseRealtimeDatabase: FirebaseDatabase
):Repository  {
    //repo
    private val currentUserAuth= authInstance.currentUser?.uid?:""
    private val firebaseRealtimeRefrence = firebaseRealtimeDatabase
    private val randomvalue = "1"

    override suspend fun signUpUser(email:String , password:String): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
       authInstance.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
           trySend(ResultState.Success(it.user?.uid.toString()))
           Log.d("SIGNUP","SUCESSFULL")
           close()
       }.addOnFailureListener {
           trySend(ResultState.Error(it.message.toString()))
           Log.d("SIGNUP","ERROR")
           close()

       }
        awaitClose {
            close()
        }
    }

    override suspend fun loginUser(email:String , password:String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
       authInstance.signInWithEmailAndPassword(email,password).addOnSuccessListener {
           trySend(ResultState.Success(it.user?.uid.toString()))
           close()
       }.addOnFailureListener {
           trySend(ResultState.Error(it.message.toString()))
           close()
       }
        awaitClose {
            close()
        }
    }

    override suspend fun getWordFromServer(): Flow<ResultState<List<String>>> = callbackFlow{
       trySend(ResultState.Loading)
        try {
            firebaseFirestore.collection(Constants.WORDS).document(Constants.LIST).get().addOnSuccessListener {
                val data = it.data?.get(Constants.WORDSFIELD) as? List<String> ?: emptyList()
                trySend(ResultState.Success(data))
            }.addOnFailureListener {
                trySend(ResultState.Error(it.message.toString()))
            }

        }catch (e:Exception){
            trySend(ResultState.Error(e.message.toString()))
            ///Error handeling

        }
        awaitClose {
            close()
        }

    }

    override suspend fun createRoomFromServer(playerData: Player): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        firebaseFirestore.collection(Constants.ROOM).document(currentUserAuth)
            .collection(Constants.PLAYERS).document(currentUserAuth).set(playerData).addOnSuccessListener {
                trySend(ResultState.Success("Room Created"))
                Log.d("Firebase", "Room created successfully")
            }.addOnFailureListener {
                trySend(ResultState.Error("Failed to Create Room"))
                Log.d("Firebase", "${it.message}")
            }
        awaitClose {
            close()
        }
    }

    override suspend fun joinRoomWithID(roomID: String,player: Player): Flow<ResultState<String>> = callbackFlow{
       trySend(ResultState.Loading)
        firebaseFirestore.collection(Constants.ROOM).document(roomID).collection(Constants.PLAYERS).add(player)
            .addOnSuccessListener {
                trySend(ResultState.Success("Joined"))
            }.addOnFailureListener {
                trySend(ResultState.Error(it.message.toString()))
            }
        awaitClose {
            close()
        }
    }


override suspend fun getAllPlayersFromRoom(roomID: String): Flow<ResultState<List<Player>>> = callbackFlow {
    trySend(ResultState.Loading)

    val listenerRegistration = firebaseFirestore.collection(Constants.ROOM)
        .document(roomID)
        .collection(Constants.PLAYERS)
        .addSnapshotListener { snapshot, error ->
            if (error != null) {
                trySend(ResultState.Error(error.message ?: "Unknown error"))
            }

            if (snapshot != null) {
                val players = snapshot.documents.mapNotNull { it.toObject(Player::class.java) }
                trySend(ResultState.Success(players))
            }
        }

    awaitClose {
        close()
    }
}

    override suspend fun uploadAllPlayersCanvasPoints(lineCordinates: Lines,roomID: String): Flow<ResultState<String>> =callbackFlow{
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

    override suspend fun getRealtimeLines(roomID: String): Flow<ResultState<LiveLine>> = callbackFlow {
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

    override suspend fun sendMessageToAllRoomMembers(roomID:String,message: Message): Flow<ResultState<String>> = callbackFlow{
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
