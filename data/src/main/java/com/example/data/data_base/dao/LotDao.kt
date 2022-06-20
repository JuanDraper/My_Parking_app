package com.example.data.data_base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data_base.entities.LotRoom

@Dao
interface LotDao {

    @Query("SELECT * FROM lotList")
    suspend fun selectFromLotList(): List<LotRoom>?

    @Query("SELECT * FROM lotList WHERE parkingId = :parkingId")
    suspend fun selectByParkingLot(parkingId: Int): LotRoom?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewLot(lot: LotRoom)

}