package com.example.restservicesimple;

import retrofit2.Retrofit;

public class AppUtils {

   private AppUtils(){

    }

    private static final String BASE_URL = "http://anbo-roomreservationv3.azurewebsites.net/api/";

    public static RoomService getRoomService() {

        return RetrofitClient.getClient(BASE_URL).create(RoomService.class);
    }
    public static ReservationServ getReservations(){
        return RetrofitClient.getClient(BASE_URL).create(ReservationServ.class);
    }
}
