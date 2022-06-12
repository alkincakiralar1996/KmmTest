package com.devalkin.kmmtestapp

import com.apollographql.apollo3.ApolloClient
import com.makswin.bifrost.Calling_codesQuery

class Greeting {
    suspend fun greeting(): List<Calling_codesQuery.Data1>? {

        val apolloClient = ApolloClient.Builder()
            .serverUrl("https://zeus.fizbot.net/graphql")
            .build()

        val callingCodesQuery = Calling_codesQuery()

        val response = apolloClient.query(callingCodesQuery).execute()

        return response.data?.calling_codes?.data
    }
}