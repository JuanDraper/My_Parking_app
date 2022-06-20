package com.example.data.data_base.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.data_base.entities.ReservationRoom

@Dao
interface ReservationDao {

    @Query("SELECT * FROM reservationList")
    suspend fun selectFromReservationList(): List<ReservationRoom>

    @Query("SELECT * FROM reservationList WHERE parkingLot = :parkingLot")
    suspend fun selectFromReservationListByParkingLot(parkingLot: Int): List<ReservationRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewReservation(reservation: ReservationRoom)

    @Query("DELETE FROM reservationList WHERE id  = :reservationId")
    suspend fun deleteReservation(reservationId: String)
}