package com.example.simplenoteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1,  exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract val noteDao:NoteDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            synchronized(this) {
                var instnace = INSTANCE
                if (instnace == null) {
                    instnace = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, "daily_notes_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instnace
                }

                return instnace
            }
        }

    }
}