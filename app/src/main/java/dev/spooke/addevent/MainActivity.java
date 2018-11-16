package dev.spooke.addevent;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText title,description,rule;
    String str_title,str_description,str_rule;
    Button addEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title =(EditText)findViewById(R.id.title);
        description =(EditText)findViewById(R.id.description);
        rule =(EditText)findViewById(R.id.rule);
        addEventButton =(Button)findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_title = title.getText().toString();
                str_description = description.getText().toString();
                str_rule = rule.getText().toString();
                addcalendar();
            }
        });
    }
    public void addcalendar(){
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType("vnd.android.cursor.item/event");

        Calendar cal = Calendar.getInstance();
        long startTime = cal.getTimeInMillis();
        long endTime = cal.getTimeInMillis()  + 60 * 60 * 1000;

        Log.e("date_check","Start Time: "+startTime+" End Time: "+endTime);

        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime);
        //intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endTime);
        //intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        intent.putExtra(CalendarContract.Events.TITLE, ""+str_title);
        intent.putExtra(CalendarContract.Events.DESCRIPTION,  ""+str_description);
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Paige Airport Parking Luton");
        intent.putExtra(CalendarContract.Events.RRULE, ""+str_rule);

        startActivity(intent);
    }
}
