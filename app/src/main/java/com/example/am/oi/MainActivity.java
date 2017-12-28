package com.example.am.oi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper db =new DataBaseHelper(this);
    }

    public void click(View view) {
        Intent intent;
        switch (view.getId()) {


            case R.id.button:
                intent = new Intent(MainActivity.this, DodajN.class);
                startActivity(intent);
                break;
        }
    }
}
