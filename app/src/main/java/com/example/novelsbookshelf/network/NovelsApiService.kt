package com.example.novelsbookshelf.network

import com.example.novelsbookshelf.model.NestedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NovelsApiService {
    @GET("books/v1/volumes")
    suspend fun getItems(
        @Query("q")
        q: String
    ): NestedResponse

    /*@GET("/books/v1/volumes/{id}")
    suspend fun getThumbnailUrl(
        @Path("id") id: String
    ): ImageLinks*/


}