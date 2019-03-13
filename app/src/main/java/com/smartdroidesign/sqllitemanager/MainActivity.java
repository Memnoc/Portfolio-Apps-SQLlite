package com.smartdroidesign.sqllitemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Populating the database
        SQLiteDatabase sqLiteDatabase = getBaseContext().openOrCreateDatabase("sqlite-test-1.db", MODE_PRIVATE, null);
        String sql = "CREATE TABLE IF NOT EXISTS contacts(name TEXT, phone INTEGER, email TEXT)";
        Log.d(TAG, "onCreate: sql is " + sql);
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO contacts VALUES('Matteo', 66236492, 'matteo@mamail.com')";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "onCreate: sql is " + sql);
        sql = "INSERT INTO contacts VALUES('John', 749823075, 'john@mamail.com');";
        sqLiteDatabase.execSQL(sql);
        Log.d(TAG, "onCreate: sql is " + sql);

        // Retrieving the data
        Cursor query = sqLiteDatabase.rawQuery("SELECT * FROM contacts;", null);
        if (query.moveToFirst()) {
            do {
                String name = query.getString(0);
                int phone = query.getInt(1);
                String email = query.getString(2);
                Toast.makeText(this, "Name = " + name + " Phone =" + phone + " Email =" + email, Toast.LENGTH_LONG).show();
            } while (query.moveToNext());
        }
        query.close(); // cursor closed
        sqLiteDatabase.close(); // -> database closed


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
