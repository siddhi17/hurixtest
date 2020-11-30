package com.example.HurixTest

import android.content.Context
import android.content.res.Resources
import androidx.room.Room

import com.kotlin.mvvm.repository.api.ApiServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [PrefrencesModule::class, ActivityModule::class, ViewModelModule::class])
class AppModule {

    companion object;

    @Singleton
    @Provides
    fun provideNewsService(): ApiServices {
        return Retrofit.Builder()
                .baseUrl("http://api.plos.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(com.example.HurixTest.LiveDataCallAdapterFactoryForRetrofit())
                .build()
                .create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "news-db").build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): NewsArticlesDao {
        return db.newsArticlesDao()
    }

    @Singleton
    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesResources(application: App): Resources = application.resources
}
