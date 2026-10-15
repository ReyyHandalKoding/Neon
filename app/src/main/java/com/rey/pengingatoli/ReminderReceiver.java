package com.rey.pengingatoli;
import android.app.*; import android.content.*; import android.os.Build;
public class ReminderReceiver extends BroadcastReceiver {
 public void onReceive(Context c,Intent i){
  int day=java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
  if(day>=10 && day<=13){
   if(Build.VERSION.SDK_INT>=26){NotificationChannel ch=new NotificationChannel("oli","Pengingat Oli",NotificationManager.IMPORTANCE_HIGH); ((NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(ch);}
   Intent open=new Intent(c,ReminderActivity.class); open.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
   PendingIntent pi=PendingIntent.getActivity(c,20,open,PendingIntent.FLAG_UPDATE_CURRENT|PendingIntent.FLAG_IMMUTABLE);
   Notification.Builder nb=Build.VERSION.SDK_INT>=26?new Notification.Builder(c,"oli"):new Notification.Builder(c);
   nb.setSmallIcon(android.R.drawable.ic_dialog_alert).setContentTitle(day==13?"PERINGATAN TERAKHIR: GANTI OLI":"PERINGATAN: GANTI OLI").setContentText("Pengingat oli motor. Buka untuk memilih OK atau BESOK.").setContentIntent(pi).setAutoCancel(true).setFullScreenIntent(pi,true);
   ((NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE)).notify(101,nb.build());
  }
  ReminderScheduler.scheduleTomorrow(c);
 }
}
