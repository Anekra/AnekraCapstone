package com.anekra.capstoneapp.data.network

import com.anekra.capstoneapp.data.network.response.game.GameResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RawgApi {
    @GET("games")
    suspend fun getGameList(
        @QueryMap queries: HashMap<String, Any>? = null
    ) : Response<GameResponse>
}