package com.sample.architecturecomponents.api

import com.sample.architecturecomponents.Login
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.POST

interface ArchApiService {
    @POST("api/login")
    fun login(@Field("email") username: String,
              @Field("password") password: String
    ): Observable<Login>
}