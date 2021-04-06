package app.prabudiworks.data.service.di

import app.prabudiworks.data.service.ConverterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ConverterModule {

    @Provides
    fun provideConverterService(retrofit: Retrofit): ConverterService {
        return retrofit.create(ConverterService::class.java)
    }

}