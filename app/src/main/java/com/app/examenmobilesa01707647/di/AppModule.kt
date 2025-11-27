package com.app.examenmobilesa01707647.di
// di/AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/covid19?country=canada")
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
    fun provideCovidRepository(
        api: CovidAPI
    ): CovidRepository {
        return CovidRepositoryImpl(api)
    }
}