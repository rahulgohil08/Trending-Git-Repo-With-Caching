package com.theworld.androidtemplatewithnavcomponents.di

import android.content.Context
import androidx.room.Room
import com.theworld.androidtemplatewithnavcomponents.data.room.db.TrendingDatabase
import com.theworld.androidtemplatewithnavcomponents.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideGameDatabase(
        @ApplicationContext appContext: Context
    ) = Room.databaseBuilder(appContext, TrendingDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()


    @Singleton
    @Provides
    fun provideTrendingDao(db: TrendingDatabase) = db.getTrendingDao()

}