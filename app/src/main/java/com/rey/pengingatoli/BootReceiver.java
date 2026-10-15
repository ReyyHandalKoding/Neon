package com.rey.pengingatoli;
import android.content.*;
public class BootReceiver extends BroadcastReceiver { public void onReceive(Context c,Intent i){ReminderScheduler.schedule(c);} }
