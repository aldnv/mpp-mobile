
package com.example.mpp.mobile.data.source

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.request


class BreedsApi {
    private val client = HttpClient(){
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
            connectTimeoutMillis = 2000
            socketTimeoutMillis = 2000
        }

        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    private val url = "https://dog.ceo/api/breeds/list"

    suspend fun getBreeds(): String {
        return client.request(url)
    }
}