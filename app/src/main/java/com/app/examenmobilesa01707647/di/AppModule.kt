package com.app.examenmobilesa01707647.di

import com.app.examenmobilesa01707647.data.remote.api.CovidApi
import com.app.examenmobilesa01707647.data.repository.CovidRepositoryImpl
import com.app.examenmobilesa01707647.domain.repository.CovidRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCovidApi(retrofit: Retrofit): CovidApi {
        return retrofit.create(CovidApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCovidRepository(api: CovidApi): CovidRepository {
        return CovidRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePreferences(@dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context): com.app.examenmobilesa01707647.data.local.CovidPreferences {
        return com.app.examenmobilesa01707647.data.local.CovidPreferences(context)
    }
}