package com.example.data.converter

import com.example.data.data_base.entities.LotRoom
import com.example.data.data_base.entities.ReservationRoom
import com.example.data.service.response.LotListResponse
import com.example.data.service.response.LotResponse
import com.example.data.service.response.ReservationListResponse
import com.example.data.service.response.ReservationResponse
import com.example.domain.entities.MappedParkingLotList
import com.example.domain.entities.MappedParkingLot
import com.example.domain.entities.MappedReservationList
import com.example.domain.entities.MappedReservation

object  Converter {

    fun parkingListResponseMapping(listResponse: LotListResponse): MappedParkingLotList {
        var lotList = ArrayList<MappedParkingLot>()
        listResponse.lotList.forEach{
            lotList.add(parkingLotResponseMapping(it))
        }
        return MappedParkingLotList(lotList)
    }

    private fun parkingLotResponseMapping(lotResponse: LotResponse): MappedParkingLot {
        return MappedParkingLot(lotResponse.parkingLot)
    }

    fun reservationListResponseMapping(listResponse: ReservationListResponse?): MappedReservationList {
        var reservationList = ArrayList<MappedReservation>()
        listResponse?.reservationList?.forEach{
            reservationList.add(reservationResponseMapping(it))
        }
        return MappedReservationList(reservationList)
    }

    private fun reservationResponseMapping(reservationResponse: ReservationResponse): MappedReservation {
        return MappedReservation(reservationResponse.id,
            reservationResponse.authorizationCode,
            reservationResponse.startDate.toLong(),
            reservationResponse.endDate.toLong(),
            reservationResponse.parkingLot
        )
    }
    fun lotRoomToLotModel(lotRoom: LotRoom?): MappedParkingLot? {
        if (lotRoom != null) {
            return MappedParkingLot(lotRoom.parkingId)
        }
        return null
    }

    fun lotRoomListToParkingLotListModel(lotRoomList: List<LotRoom>?): MappedParkingLotList {
        var lotList = mutableListOf<MappedParkingLot>()
        lotRoomList?.forEach { lot ->
            lotList.add(MappedParkingLot(lot.parkingId))
        }
        return MappedParkingLotList(lotList)
    }

    fun reservationRoomListToReservationListModel(reservationRoomList: List<ReservationRoom>?): MappedReservationList{
        var reservationListModel = mutableListOf<MappedReservation>()
        reservationRoomList?.forEach { reservation ->
            reservationListModel.add(
                MappedReservation(reservation.id,
                reservation.authorizationCode,
                reservation.start,
                reservation.end,
                reservation.parkingLot)
            )
        }
        return MappedReservationList(reservationListModel)
    }

    fun reservationRoomToReservationModel(reservationRoom: ReservationRoom?): MappedReservation{
        if (reservationRoom != null) {
            return MappedReservation(reservationRoom.id,
                reservationRoom.authorizationCode,
                reservationRoom.start,
                reservationRoom.end,
                reservationRoom.parkingLot)
        } else {
            return MappedReservation("","",0,0,0)
        }
    }
}