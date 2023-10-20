package com.xtgem.webuild.fstcawka

import android.content.Context
import androidx.room.Room
import com.xtgem.webuild.fstcawka.database.FSTCAwkaDatabase
import com.xtgem.webuild.fstcawka.misc.BaseRepository

private const val DATABASE_NAME = "fstcAwka-database"

class Repository(context: Context): BaseRepository() {

    override val database = Room
        .databaseBuilder(context.applicationContext, FSTCAwkaDatabase::class.java, DATABASE_NAME)
        .build()


    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                synchronized(context) { // stop any thread other thread from instantiating the Repository class
                    INSTANCE = Repository(context)
                }
            }
        }

        fun get(): Repository{
            return INSTANCE ?:
            throw IllegalStateException("StorageRepository must be initialized")
        }

    }


    init {
        //addTestStudent(course = true, news = true, bills = true, assignment = true )
    }

}
