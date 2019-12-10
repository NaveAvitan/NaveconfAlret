package com.example.naveconfalret;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import static android.widget.Toast.*;

/**
 * main activity, at this activity  you have 4 buttons to choose different acts
 */
public class MainActivity extends AppCompatActivity {
    String[] colors = {"red", "green", "blue"};
    int [] Selected = {0, 0, 0};
    AlertDialog.Builder adb, adb2, adb3;
    LinearLayout lin;

    /**
     * set the options menu
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    /**
     * here you move to credit activity
     *
     * @param menu
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem menu) {
        String st = menu.getTitle().toString();
        if ((st.equals("credits"))) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
        }


        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lin = findViewById(R.id.lin);

    }

    /**
     * pick color and draw the screen with that color
     * @param view
     */
    public void first(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("pick color");
        adb.setPositiveButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    lin.setBackgroundColor(Color.RED);
                }
                if (which == 2) {
                    lin.setBackgroundColor(Color.BLUE);
                }
                if (which == 1) {
                    lin.setBackgroundColor(Color.GREEN);
                }
            }

        });
        AlertDialog ad = adb.create();
        ad.show();


    }

    /**
     * set white as the screen color
     * @param view
     */
    public void three(View view) {
        lin.setBackgroundColor(Color.WHITE);
    }

    /**
     * set toast with the users massage
     * @param view
     */
    public void four(View view) {
     adb3= new AlertDialog.Builder(this);
        adb3.setCancelable(false);
        adb3.setTitle("enter massage");
     adb3.setCancelable(false);
     final EditText et=new EditText(this);
     et.setHint("type here");
     adb3.setView(et);
     adb3.setPositiveButton("set toast", new DialogInterface.OnClickListener() {
         @Override
         public void onClick(DialogInterface dialog, int which) {
             String str= et.getText().toString();
             Toast toast;
             toast = Toast.makeText(MainActivity.this,str,LENGTH_LONG);
             toast.show();
         }
     });
        adb3.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog ad = adb3.create();
        ad.show();

    }

    /**
     * pick 1 color or more and draw the screen with that color or the combined color
     * @param view
     */
    public void secend(View view) {
        adb2 =new AlertDialog.Builder(this);
        adb2.setTitle("Try the colors together");
        adb2.setCancelable(false);
        adb2.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb2.setMultiChoiceItems(colors, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) Selected[which]=255;
                else if (Selected[which]==255) Selected[which]=0;

                }


        });
        adb2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lin.setBackgroundColor(Color.rgb(Selected[0],Selected[1],Selected[2]));
                Selected [0]=0;
                Selected [1]=0;
                Selected [2]=0;
            }
        });
        AlertDialog ad = adb2.create();
        ad.show();
    }

}