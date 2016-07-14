package com.hfad.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int seconds=0;
    private boolean running;
    private boolean wasrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");      //retrieve the values of the seconds and running  and wasrunning variables from the bundle
            running = savedInstanceState.getBoolean("running");
        }
        runtimer();

    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);    //save the values of the seconds running and wasrunning variables to the bundle
        savedInstanceState.putBoolean("running", running);

    }

    /*protected  void onStop()
    {
        super.onStop();
        wasrunning=running;   //record whether the stopwatch was running when the onstop() method was called
        running=false;
    }

    protected void onStart()
    {
        super.onStart();   //implement the onstart() method.if the stopwatch was running,set it running again
        if(wasrunning)
        {
            running=true;
        }
    } */

    protected void onPause() {
        super.onPause();
        wasrunning = running;
        running = false;
    }

    protected void onResume() {
        super.onResume();
        if (wasrunning) {
            running = true;
        }
    }


    public void onclickstart(View view)
    {running=true;}

    public void onclickstop(View view)
    {running=false;}
    public void onclickreset(View view)
    {running=false;
    seconds=0;}

    private void runtimer()
    {
        final TextView timeview=(TextView) findViewById(R.id.time_view);
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timeview.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this,1);
            }
        });




    }


}
