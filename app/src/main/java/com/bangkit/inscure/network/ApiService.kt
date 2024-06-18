package com.bangkit.inscure.network

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @Multipart
    @POST("/prediction")
    fun uploadImage(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): Call<PredictionResponse>

    @POST("/inscure/add")
    fun registerUser(
        @Body requestBody: RegisterRequest
    ): Call<RegisterResponse>

    @POST("/inscure/login")
    fun loginUser(
        @Body requestBody: LoginRequest
    ): Call<LoginResponse>

    @GET("/disease")
    fun getAllDiseases(): Call<DiseaseListResponse>

    @GET("/disease/{id}")
    fun getDiseaseById(@Path("id") id: String): Call<DiseaseResponseWrapper>
}

data class PredictionResponse(
    val success: Boolean,
    val message: String,
    val code: Int,
    val data: PredictionData
)

data class PredictionData(
    val id: String,
    val gambar: String,
    val hasil_prediksi: String,
    val tgl: String,
    val user_id: String
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val notelp: String,
    val pass: String
)

data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val code: Int,
    val data: UserData
)

data class LoginRequest(
    val email: String,
    val pass: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val code: Int,
    val data: String // This is the token
)

data class UserData(
    val id: String,
    val name: String,
    val email: String,
    val notelp: String,
    val pass: String,
    val role: String
)

data class DiseaseListResponse(
    val success: Boolean,
    val message: String,
    val code: Int,
    val data: List<DiseaseResponse>
)

data class DiseaseResponse(
    val id: Int,
    val name: String,
    val headline: String,
    val description: String
)

data class DiseaseResponseWrapper(
    val success: Boolean,
    val message: String,
    val code: Int,
    val data: DiseaseResponse
)

