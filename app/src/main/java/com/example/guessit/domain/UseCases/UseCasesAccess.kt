package com.example.guessit.domain.UseCases

data class UseCasesAccess(
    val createRoomFromServerUseCase: CreateRoomFromServerUseCase,
    val getAllMessageFromRoomUseCase: GetAllMessageFromRoomUseCase,
    val getAllPlayersFromRoomUseCase: GetAllPlayersFromRoomUseCase,
    val getRealTimeLineUseCase: GetRealTimeLineUseCase,
    val getWordFromServerUseCase: GetWordFromServerUseCase,
    val joinRoomWithIDUseCase: JoinRoomWithIDUseCase,
    val loginUserUseCase: LoginUserUseCase,
    val sendMessageToAllRoomMemberUseCase: SendMessageToAllRoomMemberUseCase,
    val uploadAllPlayersCanvasPoints: UploadAllPlayersCanvasPoints,
   val uploadLineToRealTimeDataBaseUseCase: UploadLineToRealTimeDataBaseUseCase,
    val signUpUserUseCase: SignUpUserUseCase
)
