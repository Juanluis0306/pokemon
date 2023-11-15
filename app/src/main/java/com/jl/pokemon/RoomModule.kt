package com.jl.pokemon

import android.content.Context
import androidx.room.Room
import com.jl.pokemon.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTE_DATABASE_NAME = "pokemon_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, QUOTE_DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideUserDao(db: AppDataBase) = db.getBlogDao()

    @Singleton
    @Provides
    fun providePokemonDao(db: AppDataBase) = db.getPokemonDao()
}