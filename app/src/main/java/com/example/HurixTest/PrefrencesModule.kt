package com.example.HurixTest

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefrencesModule {

    @Provides
    @Singleton
    fun provideSharedPreference(app: Application): SharedPreferences =
            app.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

}
