package org.ferdyhaspin.topstories.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.ferdyhaspin.topstories.data.local.AppDatabase
import org.ferdyhaspin.topstories.data.local.dao.StoryDao
import org.ferdyhaspin.topstories.data.remote.config.ServiceGenerator
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "topstories.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideTransactionDao(database: AppDatabase): StoryDao {
        return database.storyDao()
    }

}
