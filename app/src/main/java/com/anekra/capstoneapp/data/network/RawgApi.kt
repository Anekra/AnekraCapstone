package com.anekra.capstoneapp.data.network

import com.anekra.capstoneapp.data.network.dto.game.GameDetailsResponse
import com.anekra.capstoneapp.data.network.dto.game.GameResponse
import com.anekra.capstoneapp.data.network.dto.game.GameScreenShotsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RawgApi {
    @GET("games")
    suspend fun getGameList(
        @QueryMap queries: HashMap<String, Any>? = null
    ) : Response<GameResponse>
    
    @GET("games/{id}")
    suspend fun getGameListDetails(
        @Path("id") id: String? = null
    ) : Response<GameDetailsResponse>
    
    @GET("games/{id}/screenshots")
    suspend fun getGameScreenShots(
        @Path("id") id: String? = null
    ) : Response<GameScreenShotsResponse>
}