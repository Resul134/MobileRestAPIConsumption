package com.example.restservicesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteActivity extends AppCompatActivity {

    public static final String BOOKING = "BOOKING";
    EditText userInput;
    Reservations originalReservations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);



    }

    public void goBack(View view){
        finish();
    }

    public void deleteReservation(View view){
        ReservationServ reservationService = AppUtils.getReservations();

        userInput = findViewById(R.id.deleteEditText);



        String deletebookID = userInput.getText().toString();



        int deleteIDParsed = Integer.parseInt(deletebookID);

        Call<Reservations> deleteReservationCall = reservationService.deleteReservation(deleteIDParsed);

        deleteReservationCall.enqueue(new Callback<Reservations>() {
            @Override
            public void onResponse(Call<Reservations> call, Response<Reservations> response) {
                if (response.isSuccessful())
                {
                    String message = "Reservation deleted, id: " + originalReservations.getId();
                    Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                    Log.d("DELETETAG", message);


                }
                else
                {
                    String problem = call.request().url() + "\n" + response.code() + " " + response.message();
                    Toast.makeText(getBaseContext(), problem, Toast.LENGTH_SHORT).show();
                    Log.d("DELETETAG", problem);
                }
            }

            @Override
            public void onFailure(Call<Reservations> call, Throwable t) {
                Log.e("DELETETAG", "Problem: " + t.getMessage());
            }
        });


    }


}
