package com.arlib.task.data.remote

import com.arlib.task.domain.models.DrugsResponse
import retrofit2.http.GET

interface ApiService {


    @GET("e5c2883c-06e6-4873-83c2-18c882b89682")
    suspend fun getDrugs(): DrugsResponse


}