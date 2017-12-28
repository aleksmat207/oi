package com.example.am.oi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.Toolbar;

public class DodajN extends AppCompatActivity {


    Button dodaj, btn2;
    EditText enazwa, eilosc, ekalorycznosc;

    public DodajN() {
    }

    public DodajN(Button dodaj, Button btn2, EditText enazwa, EditText eilosc, EditText ekalorycznosc) {
        this.dodaj = dodaj;
        this.btn2 = btn2;
        this.enazwa = enazwa;
        this.eilosc = eilosc;
        this.ekalorycznosc = ekalorycznosc;
    }

    public Button getDodaj() {
        return dodaj;
    }

    public void setDodaj(Button dodaj) {
        this.dodaj = dodaj;
    }

    public Button getBtn2() {
        return btn2;
    }

    public void setBtn2(Button btn2) {
        this.btn2 = btn2;
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

    public EditText getEkalorycznosc() {
        return ekalorycznosc;
    }

    public void setEkalorycznosc(EditText ekalorycznosc) {
        this.ekalorycznosc = ekalorycznosc;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_n);
        final DataBaseHelper db = new DataBaseHelper(this);

        dodaj = (Button) findViewById(R.id.dodaj);
        enazwa = (EditText) findViewById(R.id.enazwa);
        eilosc = (EditText) findViewById(R.id.eilosc);
        ekalorycznosc = (EditText) findViewById(R.id.ekalorycznosc);
        btn2 = (Button) findViewById(R.id.btn2);

        dodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean czysieudalo;

                czysieudalo = db.wstawdane(enazwa.getText().toString(), eilosc.getText().toString(), ekalorycznosc.getText().toString());
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

    public void click(View v) {
        Intent intent;
        switch (v.getId()) {

            case R.id.btn2:
                intent = new Intent(DodajN.this, Napoj.class);
                startActivity(intent);
                break;
        }
    }


}


