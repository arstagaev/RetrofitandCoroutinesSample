package com.revolve44.postmakermassive.repository

import com.revolve44.postmakermassive.api.RetrofitInstance

class MassiveRepository  {

    ///////////////////////////////////////API/////////////////////////////////////
    suspend fun getAlpha() =
        RetrofitInstance.apiAlpha.getAlfaRequest()

    suspend fun getBeta() =
        RetrofitInstance.apiBeta.getBetaRequest()

    ///////////////////////////////////Data Base///////////////////////////////////

}