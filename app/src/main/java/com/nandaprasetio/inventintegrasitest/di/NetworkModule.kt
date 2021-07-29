package com.nandaprasetio.inventintegrasitest.di

import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import com.nandaprasetio.inventintegrasitest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(provideOkHttp())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().setLenient().create()
                )
            )
            .build()
    }

    private fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(addInterceptor())
            .sslSocketFactory(getSocketFactory(), getTrustManager())
            .build()
    }

    private fun getSocketFactory(): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf(getTrustManager()), null)
        return sslContext.socketFactory
    }

    private fun getTrustManager(): X509TrustManager {
        return object: X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {}

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {}
        }
    }

    private fun addInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .addHeader("Authorization", BuildConfig.API_AUTHORIZATION)
                .build()
            it.proceed(request)
        }
    }
}