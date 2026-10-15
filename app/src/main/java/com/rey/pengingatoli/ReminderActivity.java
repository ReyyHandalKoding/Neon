package com.rey.pengingatoli;
import android.app.*; import android.os.*; import android.content.*; import android.graphics.Color; import android.graphics.Typeface; import android.view.*; import android.widget.*;
public class ReminderActivity extends Activity {
 int red=Color.rgb(255,25,50);
 public void onCreate(Bundle b){super.onCreate(b);getWindow().setStatusBarColor(Color.BLACK);getWindow().setNavigationBarColor(Color.BLACK);getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); render();}
 void render(){
  LinearLayout l=new LinearLayout(this); l.setOrientation(1); l.setGravity(Gravity.CENTER); l.setPadding(24,28,24,28); l.setBackgroundColor(Color.rgb(5,2,5));
  TextView warning=new TextView(this); warning.setText("⚠  PERINGATAN"); warning.setTextColor(red);warning.setTextSize(24);warning.setGravity(17);warning.setTypeface(null,Typeface.BOLD);l.addView(warning);
  TextView title=new TextView(this);title.setText("GANTI OLI\nMOTOR KAMU!");title.setTextColor(Color.WHITE);title.setTextSize(34);title.setGravity(17);title.setTypeface(null,Typeface.BOLD);title.setPadding(0,20,0,12);l.addView(title);
  TextView bike=new TextView(this);bike.setText("🏍️\n🛢️  🔧");bike.setTextSize(54);bike.setGravity(17);bike.setTextColor(red);l.addView(bike);
  int day=java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH);
  TextView msg=new TextView(this);msg.setText(day==13?"INI PERINGATAN TERAKHIR!\nBesok batas siklus penggantian oli.":"Saatnya cek dan ganti oli motor.\nJangan abaikan perawatan mesin!");msg.setTextColor(0xffff9da8);msg.setTextSize(18);msg.setGravity(17);msg.setPadding(0,20,0,30);l.addView(msg);
  Button ok=new Button(this);ok.setText("✓  OK  (SUDAH GANTI OLI)");ok.setTextColor(Color.WHITE);ok.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(200,0,30)));l.addView(ok);
  Button later=new Button(this);later.setText("◷  BESOK  (INGATKAN LAGI)");later.setTextColor(Color.WHITE);later.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.rgb(50,10,15)));l.addView(later);
  ok.setOnClickListener(v->{getSharedPreferences("oil",0).edit().putBoolean("done",true).putInt("doneMonth",java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)).apply(); ((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).cancel(101); ReminderScheduler.schedule(this);finish();});
  later.setOnClickListener(v->{((NotificationManager)getSystemService(NOTIFICATION_SERVICE)).cancel(101);ReminderScheduler.scheduleTomorrow(this);finish();});
  setContentView(l);
 }
}
