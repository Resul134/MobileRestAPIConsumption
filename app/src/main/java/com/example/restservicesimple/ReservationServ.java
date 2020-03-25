package com.example.restservicesimple;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReservationServ {
    @GET("reservations")
    Call<List<Reservations>> getAllReservations();

    @GET("reservations/{reservationId}")
    Call<Reservations> getReservationById(int reservationId);


    //Use this one for reservations
    @GET("reservations/{roomId}/{fromTime}")
    Call<Reservations> getReservationbyIDandTime(int roomId, int fromTime);

    @POST("reservations")
    @FormUrlEncoded
    Call<Reservations> saveReservation(@Field("fromTime") int fromTime, @Field("toTime") int toTime,
                                @Field("userId") int userId, @Field("purpose") String purpose, @Field("roomId") int roomId) ;

    @POST("reservations")
    Call<Reservations> saveBookBody(@Body Reservations reservations);

    @DELETE("reservations/{id}")
    Call<Reservations> deleteReservation(@Path("id") int id);

    @PUT("reservations/{id}")
    Call<Reservations> updateReservation(@Path("id") int id, @Body Reservations reservations);
}
