package org.careerop.mydiary;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addNoteFab;

    private ListView noteListView;
    private DbHelper dbHelper;
    private List<NoteList> noteLists = new ArrayList<>();
    private NoteListAdapter noteListAdapter;

    private String title = null, body = null, intentId = null;
    private SharedPreferences sharedPreferences;
    private String username;
    private String password;
    private String DEFAULT = "N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DbHelper(this);
        noteListView = (ListView) findViewById(R.id.noteListView);


        addNoteFab = (FloatingActionButton) findViewById(R.id.add_note_fab);
        addNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Note.class);
                intent.putExtra("intentId", intentId);
                intent.putExtra("title", title);
                intent.putExtra("body", body);
                startActivity(intent);

            }
        });

        sharedPreferences = getSharedPreferences("My Data", Context.MODE_PRIVATE);

        username = sharedPreferences.getString("username", DEFAULT);
        password = sharedPreferences.getString("password", DEFAULT);

        if (username.equals(DEFAULT) || password.equals(DEFAULT)) {


            Toast.makeText(getApplicationContext(), "Please Login first.", Toast.LENGTH_LONG).show();


            try {
                sleep(1500);

                startActivity(new Intent(MainActivity.this, LoginRegister.class));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } else {
            showAllNotes();

        }

    }

    private void showAllNotes() {

        final Cursor result = dbHelper.getAllNotes();

        // if there have no notes then result.getCount() return 0
        if (result.getCount() == 0) {

            Toast.makeText(getApplicationContext(), "No Notes found", Toast.LENGTH_LONG).show();

            return;

        } else {

            noteListAdapter = new NoteListAdapter(this, noteLists);
            noteListView.setAdapter(noteListAdapter);

            //if more result have occure
            while (result.moveToNext()) {

                NoteList lists = new NoteList();
                lists.setId(result.getString(0));
                lists.setTitle(result.getString(1));
                lists.setBody(result.getString(2));
                lists.setDate(result.getString(3));

                //Add all notes data to NoteLists ArrayList from database
                noteLists.add(lists);


            }

/*            noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    title = noteLists.get(position).getTitle().toString();
                    body = noteLists.get(position).getBody().toString();
                    intentId = noteLists.get(position).getId().toString();

                    if (username.equals(DEFAULT) || password.equals(DEFAULT)) {
                        startActivity(new Intent(getApplicationContext(), LoginRegister.class));
                    } else {
                        Intent intent = new Intent(getApplicationContext(), Note.class);
                        intent.putExtra("intentId", intentId);
                        intent.putExtra("title", title);
                        intent.putExtra("body", body);
                        startActivity(intent);
                    }

                    //Toast.makeText(getApplicationContext(),noteLists.get(position).getBody().toString(),Toast.LENGTH_LONG).show();

                }
            });*/

            noteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.deleteNote(noteLists.get(position).getId());
                    Toast.makeText(MainActivity.this, "Successfully deleted " + noteLists.get(position).getTitle().toString(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        }

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
        if (id == R.id.developer) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("Developers");
            dialog.setMessage("Name: Lamia Islam Mim" + "\nId:1302055" + "\nReg: 04229" + "\nSession: 2013-2014");
            dialog.setIcon(R.drawable.lamia);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });
            dialog.show();
            return true;

        }

        if (id == R.id.logout) {

            sharedPreferences = getSharedPreferences("My Data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("username");
            editor.remove("password");
            editor.apply();

            Toast.makeText(getApplicationContext(), "Logout completed.", Toast.LENGTH_LONG).show();
        }

        if (id == R.id.exit) {

            new android.support.v7.app.AlertDialog.Builder(this)
                    .setTitle(getTitle().toString())
                    .setMessage("Do you want to exit?")
                    .setCancelable(true)
                    .setIcon(R.drawable.lamia)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MainActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }
}
