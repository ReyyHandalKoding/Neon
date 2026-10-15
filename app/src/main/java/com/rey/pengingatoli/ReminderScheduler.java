package com.rey.pengingatoli;
import android.app.*; import android.content.*; import java.util.*;
public class ReminderScheduler {
 public static void schedule(Context c){
  AlarmManager am=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
  Calendar now=Calendar.getInstance(); Calendar next=(Calendar)now.clone(); next.set(Calendar.DAY_OF_MONTH,10); next.set(Calendar.HOUR_OF_DAY,9); next.set(Calendar.MINUTE,0); next.set(Calendar.SECOND,0);
  if(now.after(next)) next.add(Calendar.MONTH,1);
  Intent i=new Intent(c,ReminderReceiver.class); PendingIntent pi=PendingIntent.getBroadcast(c,10,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  try { if(android.os.Build.VERSION.SDK_INT>=23) am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,next.getTimeInMillis(),pi); else am.setExact(AlarmManager.RTC_WAKEUP,next.getTimeInMillis(),pi); } catch(Exception e){am.set(AlarmManager.RTC_WAKEUP,next.getTimeInMillis(),pi);}
 }
 public static void scheduleTomorrow(Context c){
  AlarmManager am=(AlarmManager)c.getSystemService(Context.ALARM_SERVICE); Calendar n=Calendar.getInstance(); n.add(Calendar.DAY_OF_MONTH,1); n.set(Calendar.HOUR_OF_DAY,9);n.set(Calendar.MINUTE,0);n.set(Calendar.SECOND,0);
  Intent i=new Intent(c,ReminderReceiver.class); PendingIntent pi=PendingIntent.getBroadcast(c,10,i,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
  try{am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,n.getTimeInMillis(),pi);}catch(Exception e){am.set(AlarmManager.RTC_WAKEUP,n.getTimeInMillis(),pi);}
 }
}
