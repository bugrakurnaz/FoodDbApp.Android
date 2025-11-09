package com.bkurnaz.fooddbapp.network

import com.bkurnaz.fooddbapp.model.Category
import retrofit2.http.GET

interface CategoriesApi {
    @GET("api/categories")
    suspend fun getAll(): List<Category>
}
