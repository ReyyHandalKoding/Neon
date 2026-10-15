package com.rey.pengingatoli;

import android.app.*;
import android.os.*;
import android.content.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {
    LinearLayout root;
    TextView status;
    int red=Color.rgb(255,35,55);
    public void onCreate(Bundle b){super.onCreate(b); getWindow().setStatusBarColor(Color.BLACK); getWindow().setNavigationBarColor(Color.BLACK); build(); requestNotification(); }
    void build(){
        root=new LinearLayout(this); root.setOrientation(1); root.setPadding(24,32,24,24); root.setBackgroundColor(Color.rgb(8,5,8));
        ScrollView sc=new ScrollView(this); sc.addView(root);
        TextView title=new TextView(this); title.setText("⚠ PENGINGAT OLI"); title.setTextColor(red); title.setTextSize(29); title.setTypeface(null,Typeface.BOLD); title.setGravity(17); root.addView(title);
        TextView sub=new TextView(this); sub.setText("MOTOR SELALU PRIMA  •  NEON RED SYSTEM"); sub.setTextColor(Color.WHITE); sub.setGravity(17); sub.setPadding(0,8,0,20); root.addView(sub);
        TextView art=new TextView(this); art.setText("🏍️\n\n🛢️  GANTI OLI TEPAT WAKTU\n━━━━━━━━━━━━━━━━"); art.setTextColor(red); art.setTextSize(24); art.setGravity(17); art.setPadding(10,25,10,25); root.addView(art);
        TextView info=new TextView(this); info.setText("JADWAL OTOMATIS\nTanggal 10: pengingat utama\nTanggal 11–12: pengingat ulang\nTanggal 13: peringatan terakhir\nTanggal 14: batas siklus\n\nPilih BESOK untuk menunda satu hari. Pilih OK setelah oli benar-benar diganti."); info.setTextColor(Color.WHITE); info.setTextSize(17); info.setPadding(16,18,16,18); info.setBackgroundColor(Color.rgb(28,8,12)); root.addView(info);
        status=new TextView(this); status.setTextColor(0xff7CFF9B); status.setTextSize(15); status.setPadding(0,20,0,16); root.addView(status); updateStatus();
        Button enable=button("AKTIFKAN SISTEM PENGINGAT"); enable.setOnClickListener(v->{requestExactAlarm(); ReminderScheduler.schedule(this); updateStatus(); Toast.makeText(this,"Jadwal pengingat diaktifkan",Toast.LENGTH_LONG).show();}); root.addView(enable);
        Button test=button("TES POPUP SEKARANG"); test.setOnClickListener(v->{startActivity(new Intent(this,ReminderActivity.class));}); root.addView(test);
        Button permission=button("BUKA IZIN NOTIFIKASI"); permission.setOnClickListener(v->{if(Build.VERSION.SDK_INT>=33) requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"},5); else Toast.makeText(this,"Notifikasi tersedia",0).show();}); root.addView(permission);
        setContentView(sc);
    }
    Button button(String s){Button b=new Button(this); b.setText(s); b.setTextColor(Color.WHITE); b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(170,0,20))); LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,-2); p.setMargins(0,10,0,4); b.setLayoutParams(p); return b;}
    void updateStatus(){status.setText("● SISTEM SIAP\nJadwal: setiap tanggal 10–13, berulang bulanan.");}
    void requestNotification(){if(Build.VERSION.SDK_INT>=33 && checkSelfPermission("android.permission.POST_NOTIFICATIONS")!=0) requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"},5);}
    void requestExactAlarm(){if(Build.VERSION.SDK_INT>=31){AlarmManager a=(AlarmManager)getSystemService(ALARM_SERVICE); if(!a.canScheduleExactAlarms()){try{startActivity(new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM));}catch(Exception e){}}}}
}
