package com.example.user.datepickerdemo;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ImageButton b1;
    private ImageButton b2;
    private DatePickerDialog datePickerDialog;
    private DatePicker   datePicker;
    private TextView t1,t2,year,month,date;
    private Button b;
    private Button c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        b1 = findViewById(R.id.todaycalenderid);
        b2 = findViewById(R.id.birthdaycalenderid);
        b= findViewById(R.id.calculateid);
        c= findViewById(R.id.clearid);
        year = findViewById(R.id.yearid);
        month = findViewById(R.id.monthid);
        date = findViewById(R.id.dateid);


        t1 = findViewById(R.id.todayinfoid);
        t2 = findViewById(R.id.birthdayinfoid);
        //set current date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate =mdformat.format(calendar.getTime());
        t1.setText(strDate);
        t2.setText(strDate);




    }

    public void picker(View view) {
        if(view.getId() == R.id.todaycalenderid)
        {
            datePicker = new DatePicker(this);
            int currentdate =  datePicker.getDayOfMonth();
            int currentmonth =  datePicker.getMonth();
            int currentyear =  datePicker.getYear();


            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            t1.setText(dayOfMonth+"-"+month+"-"+year);

                        }
                    },currentyear,currentmonth,currentdate);

            datePickerDialog.show();

        }
        if(view.getId() == R.id.birthdaycalenderid)
        {
            datePicker = new DatePicker(this);
            int currentdate =  datePicker.getDayOfMonth();
            int currentmonth =  datePicker.getMonth();
            int currentyear =  datePicker.getYear();

            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            t2.setText(dayOfMonth+"-"+month+"-"+year);

                        }
                    },currentyear,currentmonth,currentdate);

            datePickerDialog.show();

        }
        if(view.getId() == R.id.calculateid)
        {
            if(t1.getText().toString().equals(t2.getText().toString()))
            {
                year.setText("0");
                month.setText("0");
                date.setText("0");

            }
            else
            {

                String today = t1.getText().toString();
                String birthday = t2.getText().toString();
                String todaydate="",tomonth="",toyear="";


                String tostrArray[] = today.split("-");
                String birthstrArray[] = birthday.split("-");

                //Collect to info
                for(int i=0; i < tostrArray.length; i++){
                    if(todaydate.equals(""))
                    {
                        todaydate = tostrArray[i];
                        continue;
                    }
                    if(tomonth.equals(""))
                    {
                        tomonth = tostrArray[i];
                        continue;
                    }
                    if(toyear.equals(""))
                    {
                        toyear = tostrArray[i];
                        continue;
                    }
                }

                //Collect Birth info
                String birthdaydate="",birthtomonth="",birthtoyear="";
                for(int i=0; i < birthstrArray.length; i++){
                    if(birthdaydate.equals(""))
                    {
                        birthdaydate = birthstrArray[i];
                        continue;
                    }
                    if(birthtomonth.equals(""))
                    {
                        birthtomonth = birthstrArray[i];
                        continue;
                    }
                    if(birthtoyear.equals(""))
                    {
                        birthtoyear = birthstrArray[i];
                        continue;
                    }
                }

                //convert int today
                int actualtodaydate = Integer.parseInt(todaydate);
                int actualtodaymonth = Integer.parseInt(tomonth);
                int actualtodayYear = Integer.parseInt(toyear);


                //convert int Birthday
                int actualBirthdaydate = Integer.parseInt(birthdaydate);
                int actualBirthdaymonth = Integer.parseInt(birthtomonth);
                int actualBirthdayYear = Integer.parseInt(birthtoyear);

                if(actualtodayYear < actualBirthdayYear)
                {
                    Toast.makeText(MainActivity.this,"Birthday Year is Greater than Current Year",Toast.LENGTH_LONG).show();
                }
                else
                {
                    int resultday=0,resultmonth=0,resultyear = 0;
                    //calculate date
                    if(actualtodaydate < actualBirthdaydate)
                    {
                        resultday = (actualtodaydate+30)-actualBirthdaydate;
                        actualBirthdaymonth = actualBirthdaymonth+1;

                    }
                    else
                    {
                        resultday = actualtodaydate-actualBirthdaydate;
                    }

                    //calculate Month
                    if(actualtodaymonth < actualBirthdaymonth)
                    {
                        resultmonth = (actualtodaymonth+12)-actualBirthdaymonth;
                        actualBirthdayYear = actualBirthdayYear+1;

                    }
                    else
                    {
                        resultmonth = actualtodaymonth-actualBirthdaymonth;
                    }
                    //calculate Year
                    if(actualtodayYear >= actualBirthdayYear)
                    {
                        resultyear = actualtodayYear-actualBirthdayYear;
                    }

                        //String.valueOf(int)
                        year.setText(String.valueOf(resultyear));
                        month.setText(String.valueOf(resultmonth));
                        date.setText(String.valueOf(resultday));
                    //System.out.println("Result \n"+"Day ="+resultday+"\nMonth ="+resultmonth+"\nYear= "+resultyear);
                }



            }


        }
        if(view.getId() == R.id.clearid)
        {
            //set current date
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate =mdformat.format(calendar.getTime());
            t2.setText(strDate);
            t1.setText(strDate);
            year.setText("Year");
            month.setText("Month");
            date.setText("Days");
        }



    }
}
