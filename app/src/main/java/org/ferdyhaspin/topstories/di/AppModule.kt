package org.ferdyhaspin.topstories.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.ferdyhaspin.topstories.data.remote.config.ServiceGenerator
import java.util.*
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideServiceGenerator(
        @ApplicationContext context: Context,
        gson: Gson
    ): ServiceGenerator {
        return ServiceGenerator(context, gson)
    }

    @Provides
    @Singleton
    fun provideLocale(): Locale {
        return Locale("in")
    }

}
