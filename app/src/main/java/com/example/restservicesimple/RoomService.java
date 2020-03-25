package com.example.restservicesimple;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Body;

public interface RoomService {


    @GET("reservations")
    Call<List<Rooms>> getAllRooms();

    @GET("rooms/{id}")
    Call<Rooms> getroomById(int id);


    //use this one
    @GET("rooms/free/{time}")
    Call<List<Rooms>> getfreeRoomsByTime(@Path("time") int time);

    @DELETE("rooms/{id}")
    Call<Rooms> deleteBook(@Path("id") int id);

    @PUT("rooms/{id}")
    Call<Rooms> updateBook(@Path("id") int id, @Body Rooms room);



}
