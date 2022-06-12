package com.devalkin.kmmtestapp

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Query

class Greeting {
    suspend fun greeting(): String {

        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://example.com/graphql")
            .build()

        return "Hello, ${Platform().platform}!"
    }
}