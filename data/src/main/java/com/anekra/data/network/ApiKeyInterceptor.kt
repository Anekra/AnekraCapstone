package com.anekra.data.network

import com.anekra.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return with(chain.request()) {
            newBuilder()
                .url(
                    url.newBuilder()
                        .addQueryParameter("key", BuildConfig.API_KEY)
                        .build()
                )
                .build()
                .let(chain::proceed)
        }
    }
}