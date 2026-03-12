package com.smartelderly.community.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smartelderly.community.data.room.entity.UserEntity

/**
 * 数据库
 */
@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "smart_elderly_db"
        private var instance: AppDatabase? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): AppDatabase {
            return instance!!
        }
    }
}
