package com.si.spinnerpranish;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private Spinner spincountry,spinplayer;
    private AutoCompleteTextView autocompletetextview;
    private TextView datepicker,timepicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spincountry = findViewById(R.id.spincountry);
        spinplayer = findViewById(R.id.spinplayer);
        autocompletetextview = findViewById(R.id.autocompletetextview);
        datepicker = findViewById(R.id.datepicker);
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePicker();

            }
        });
        timepicker = findViewById(R.id.timepicker);
        timepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadtimepicker();

            }
        });


        String  countries[] = {"Select Your Country","Nepal","China","India","Japan","Korea"};
        final String nepaliplayer[] = {"Parash","pranish","Gynandra","Sompal"};
        final String indianplayer[] = {"Virat","Dhoni","Sachin"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,countries
        );
        spincountry.setAdapter(adapter);

        spincountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spincountry.getSelectedItem().toString().equals("India")){
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1,indianplayer);
                    spinplayer.setAdapter(arrayAdapter);
                }
                        else{
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1,nepaliplayer);
                    spinplayer.setAdapter(arrayAdapter);

            }}






            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(
                this,android.R.layout.select_dialog_item,nepaliplayer
        );
        autocompletetextview.setAdapter(stringArrayAdapter);
        autocompletetextview.setThreshold(1);//will start after 1 albhanet

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
String date = "Month/Day/Year :"  +month+ "/"+dayOfMonth+"/"+year;
datepicker.setText(date);
    }



    private void loadDatePicker (){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this,year,month,day
        );
        datePickerDialog.show();

    }


    private void loadtimepicker (){
        final Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this,hour,minute,second
        );
        datePickerDialog.show();

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String ampm;
                if (hourOfDay >= 12) {
                    hourOfDay -= 12;
                    ampm = "PM";
                } else {
                    ampm = "AM";
                }
                timepicker.setText(("Time is: " + hourOfDay + ":" + minute + "" + ampm).toString());

            }
        },hour,minute,false);
        timePickerDialog.show();

    }

}


