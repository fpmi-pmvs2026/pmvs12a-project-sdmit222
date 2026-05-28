package com.egorova.activitytracker.data

import retrofit2.http.GET

interface ApiService {
    @GET("activity") // Пример эндпоинта
    suspend fun getActivity(): String
}