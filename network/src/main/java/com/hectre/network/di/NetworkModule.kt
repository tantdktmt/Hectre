package com.hectre.network.di

import android.content.Context
import android.content.pm.ApplicationInfo
import com.hectre.config.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT = 60L
    private const val MINIMUM_FETCH_INTERVAL = 30L
    private const val MINIMUM_FETCH_INTERVAL_PROD = 300L

    @Provides
    @Singleton
    fun provideLoggingInterceptor(@ApplicationContext appContext: Context): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level =
            if (isDebuggable(appContext)) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return logging
    }

    @Provides
    @Singleton
    fun provideDispatcher(): Dispatcher {
        val dispatcher = Dispatcher().apply {
            maxRequests = 1
        }
        return dispatcher
    }

    @Provides
    @Suppress("LongParameterList")
    fun provideOkHttpClientBuilder(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        dispatcher: Dispatcher,
        connectionSpec: ConnectionSpec
    ): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient().newBuilder()
            .dispatcher(dispatcher)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectionSpecs(arrayListOf(connectionSpec, ConnectionSpec.CLEARTEXT))
            .addInterceptor(httpLoggingInterceptor)
        return httpClientBuilder
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideStandardRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return buildRetrofit(gsonConverterFactory, okHttpClient)
    }

    private fun isDebuggable(appContext: Context) =
        0 != appContext.packageManager?.getApplicationInfo(
            appContext.packageName!!,
            0
        )?.flags?.and(ApplicationInfo.FLAG_DEBUGGABLE) ?: false

    private fun buildRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.API_BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }
}
