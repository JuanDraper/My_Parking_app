package com.example.data.data_base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.data_base.dao.LotDao
import com.example.data.data_base.dao.ReservationDao
import com.example.data.data_base.entities.LotRoom
import com.example.data.data_base.entities.ReservationRoom


@Database(version = 1, entities = [ReservationRoom::class, LotRoom::class])
abstract class ParkingDataBase: RoomDatabase() {
    companion object{

        private const val DATABASE_NAME = "ParkingLotDataBase"
        private lateinit var instance: ParkingDataBase

        @Synchronized
        fun getInstance( context: Context): ParkingDataBase{
            if(!this::instance.isInitialized) {
                instance = Room.databaseBuilder(context.applicationContext,
                    ParkingDataBase::class.java,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
    abstract fun reservationDataBaseDao(): ReservationDao
    abstract fun lotDao(): LotDao
}