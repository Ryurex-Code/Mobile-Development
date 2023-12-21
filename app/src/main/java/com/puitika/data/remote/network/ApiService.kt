package com.puitika.data.remote.network

import com.puitika.data.remote.response.BiodataResponse
import com.puitika.data.remote.response.ClothResponse
import com.puitika.data.remote.response.CreateEventResponse
import com.puitika.data.remote.response.EventResponse
import com.puitika.data.remote.response.LoginResponse
import com.puitika.data.remote.response.RegionResponse
import com.puitika.data.request.RegisterRequest
import com.puitika.data.remote.response.RegisterResponse
import com.puitika.data.remote.response.ScanResponse
import com.puitika.data.request.CreateEventRequest
import com.puitika.data.request.LoginRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.Part


interface ApiService {
    @POST("/register")
    suspend fun register(@Body body: RegisterRequest): RegisterResponse

    @POST("/login")
    suspend fun login(@Body body: LoginRequest) : LoginResponse

    @GET("/me")
    suspend fun getBiodata(@Header("ApiKey") apiKey: String): BiodataResponse

    @GET("/region")
    suspend fun getRegion(): RegionResponse

    @GET("/cloth")
    suspend fun getCloth(): ClothResponse

    @GET("/events")
    suspend fun getEvent(): EventResponse
    
    @Headers("Accept: application/json")
    @POST("/createEvent")
    suspend fun createEvent(@Body body : CreateEventRequest) : CreateEventResponse

    @Multipart
    @POST("/scan")
    suspend fun scanCloth(
        @Part file: MultipartBody.Part,
    ): ScanResponse
}
