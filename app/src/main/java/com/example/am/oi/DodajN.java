package com.example.am.oi;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.am.oi.DataBaseHelper.database_name;

public class DodajN extends AppCompatActivity {


    public static int napoje;

    Button dodaj, pokaz, usun, stat;
    EditText enazwa, eilosc, podajID;

    public static int getNapoje() {
        return napoje;
    }

    public static void setNapoje(int napoje) {
        DodajN.napoje = napoje;
    }

    public DodajN() {
    }

    public DodajN(Button dodaj,EditText podajID, EditText enazwa, Button usun, EditText eilosc, Button pokaz, Button stat) {
        this.dodaj = dodaj;
        this.usun=usun;
        this.enazwa = enazwa;
        this.stat=stat;
        this.eilosc = eilosc;
        this.podajID=podajID;
        this.pokaz = pokaz;

    }

    public Button getDodaj() {
        return dodaj;
    }

    public Button getStat() {
        return stat;
    }

    public void setStat(Button stat) {this.stat = stat;
    }

    public void setDodaj(Button dodaj) {
        this.dodaj = dodaj;
    }

    public Button getUsun() {
        return usun;
    }

    public void setUsun(Button usun) {
        this.usun = usun;
    }

    public EditText getPodajID() {
        return podajID;
    }

    public void setPodajID(EditText podajID) {
        this.podajID = podajID;
    }

    public Button getPokaz() {
        return pokaz;
    }

    public void setPokaz(Button pokaz) {
        this.pokaz = pokaz;
    }

    public EditText getEnazwa() {
        return enazwa;
    }

    public void setEnazwa(EditText enazwa) {
        this.enazwa = enazwa;
    }

    public EditText getEilosc() {
        return eilosc;
    }

    public void setEilosc(EditText eilosc) {
        this.eilosc = eilosc;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyTimerTask myTask = new MyTimerTask();
        Timer myTimer = new Timer();
        int czas = 360000*6;
        myTimer.schedule(myTask, 5000, 2160000); //co godzinę
            String col2;
      

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_n);

        final DataBaseHelper db = new DataBaseHelper(this);
        usun= (Button) findViewById(R.id.usun);
        dodaj = (Button) findViewById(R.id.dodaj);
        podajID=(EditText) findViewById(R.id.podajID) ;
        stat=(Button) findViewById(R.id.stat);
        enazwa = (EditText) findViewById(R.id.enazwa);
        eilosc = (EditText) findViewById(R.id.eilosc);
        //ekalorycznosc = (EditText) findViewById(R.id.ekalorycznosc);


        pokaz = (Button) findViewById(R.id.pokaz);
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent;
                    intent = new Intent(DodajN.this, Napoj.class);
                    intent.putExtra("napoje", napoje);

                    startActivity(intent);
            }
        });
        usun.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if (db.usunRekordy(podajID.getText().toString())) {
                    Toast.makeText(DodajN.this, "Usunięto wpis", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(DodajN.this, "Wystąpił błąd. Spróbuj ponownie", Toast.LENGTH_LONG).show();
                }
            }
        });

        pokaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteCursor kursor = db.pobierzDane();
                if (kursor.getCount() > 0) {
                    StringBuffer buff = new StringBuffer();
                    while (kursor.moveToNext()) {
                        buff.append("ID:" + kursor.getString(0) + "\n");
                        buff.append("NAPOJ:" + kursor.getString(1) + "\n");
                        buff.append("ILOSC:" + kursor.getString(2) + "\n");

                    }
                PokazWiadomosc("Ile dzisiaj wypiłeś?", buff.toString());
                }else
                PokazWiadomosc("Ile dzisiaj wypiłeś?", "Coś poszło nie tak");
            }
               });

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean czysieudalo;

                czysieudalo = db.wstawdane(enazwa.getText().toString(), eilosc.getText().toString());
                if (czysieudalo) {
                    Toast.makeText(DodajN.this, "Dodanie napoju do bazy powiodło się", Toast.LENGTH_LONG).show();
//                    NotificationCompat.Builder mBuilder =
//                            new NotificationCompat.Builder(getApplicationContext())
//                                    .setSmallIcon(R.drawable.ic_launcher_background)
//                                    .setContentTitle(" Dodanie napoju do bazy powiodło się")
//                                    .setContentText("dodanie");
//
//                    int mNotificationId = 001;
//                    NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                    if (mNotifyMgr != null) {
//                        mNotifyMgr.notify(mNotificationId, mBuilder.build());
//                    }

                } else {
                    Toast.makeText(DodajN.this, "Dodanie napoju do bazy nie powiodło się", Toast.LENGTH_LONG).show();
                }


            }

        });
    }

    public void PokazWiadomosc(String tytul, String Wiad) {
        AlertDialog.Builder bud = new AlertDialog.Builder(this);
        bud.setCancelable(true);
        bud.setMessage(Wiad);
        bud.setTitle(tytul);
        bud.show();
    }

    public void click(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.pokaz:
                intent = new Intent(DodajN.this, Napoj.class);
                startActivity(intent);
                break;

        }
    }


    class MyTimerTask extends TimerTask {
        public void run() {

            generateNotification(getApplicationContext(), "Czas na szklankę wody! :)");
        }
    }

    private void generateNotification(Context context, String message) {

        int icon = R.drawable.ic_notifications_black_24dp;
        long when = System.currentTimeMillis();

        String appname = context.getResources().getString(R.string.app_name);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        Notification notification;
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        // To support 2.3 os, we use "Notification" class and 3.0+ os will use
        // "NotificationCompat.Builder" class.
        long[] pattern = {500, 500};
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context);
        notification = builder.setContentIntent(contentIntent)
                .setSmallIcon(icon).setTicker(appname).setWhen(0)
                .setAutoCancel(true).setContentTitle(appname)
                .setContentText(message).setVibrate(pattern).setSound(sound).build();

        notificationManager.notify((int) when, notification);

    }

}



