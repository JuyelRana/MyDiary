package org.careerop.mydiary;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;

public class Note extends AppCompatActivity {

    private EditText etTitle, etBody;
    private TextView txtDate;
    private String curDate = "";
    private String title, body;
    private String intentTitle, intentBody, intentId;
    private FloatingActionButton addSingleNoteFab, deleteSingleNoteFab;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etTitle = (EditText) findViewById(R.id.etTitle);
        etBody = (EditText) findViewById(R.id.etBody);
        txtDate = (TextView) findViewById(R.id.txtDate);
        addSingleNoteFab = (FloatingActionButton) findViewById(R.id.addSingleNoteFab);
        deleteSingleNoteFab = (FloatingActionButton) findViewById(R.id.deleteSingleNoteFab);


        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);
        SimpleDateFormat formatter = new SimpleDateFormat("d'/'M'/'y");
        curDate = formatter.format(curDateTime);
        txtDate.setText("" + curDate);

        dbHelper = new DbHelper(this);


        //get notes value from another activity for update or delete
        intentTitle = getIntent().getExtras().getString("title");
        intentBody = getIntent().getExtras().getString("body");
        intentId = getIntent().getExtras().getString("intentId");

        if (intentBody != null && intentTitle != null) {
            etTitle.setText(intentTitle);
            etBody.setText(intentBody);
            etBody.setEnabled(false);
            etTitle.setEnabled(false);
        }


        addSingleNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = etTitle.getText().toString();
                body = etBody.getText().toString();


                NotePad notePad = new NotePad(title, body, curDate);


                long inserted = dbHelper.insertNote(notePad);

                if (inserted == -1) {
                    Toast.makeText(getApplicationContext(), "Date note saveed!", Toast.LENGTH_LONG).show();
                } else {

                    //if insertion is successfull to database
                    Toast.makeText(getApplicationContext(), "Note Date Successfully Saved!", Toast.LENGTH_LONG).show();

                    etTitle.setText("");
                    etBody.setText("");

                    try {
                        sleep(1000);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        deleteSingleNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dbHelper.deleteNote(intentId)) {

                    etTitle.setText("");
                    etBody.setText("");

                    Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_LONG).show();

                    etTitle.setText("");
                    etBody.setText("");

                    try {
                        sleep(1000);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {

                    Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
