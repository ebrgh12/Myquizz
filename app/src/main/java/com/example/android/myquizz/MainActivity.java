package com.example.android.myquizz;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    private int finalScore = 0;
    private int wrongSol = 5;
    private CheckBox opt1;
    private boolean temp_opt1;
    private CheckBox opt2;
    private boolean temp_opt2;
    private CheckBox opt3;
    boolean temp_opt3;
    private String answer3;
    private String s1;
    private String answer5;
    private String s2;
    private Button answerbutton4;
    private Button answerbutton5;
    private Editable nameEditable3;
    private EditText answerField3;
    private Editable nameEditable5;
    private EditText answerField5;
    private RadioButton r1;
    private RadioButton r2;
    private int reset =0;
    private int submit=0;
    private int f1 = 0;  /* Here we basically make all the variables private as other classes should not access these variables*/
    private int se2 = 0; //Becareful while using onlick in the XML code....this will create problem if you dont invoke that onclick method
    private int t3 = 0;
    private int f4 = 0;
    private int f5 = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Below is the code for checkbox for the first question, these variables opt1,opt2, opt3 are made global and we dont specify
        //the data type again as, the compiler would redeclare it.
        opt1 = (CheckBox) findViewById(R.id.checkbox1);
        opt2 = (CheckBox) findViewById(R.id.checkbox2);
        opt3 = (CheckBox) findViewById(R.id.checkbox3);
        // Second question uses Radio buttons and radio group. Here we use r1 and r2 as reference varibles which will be used in
        //in the reset method to uncheck it when we press the Restart button.
        r1 = (RadioButton) findViewById(R.id.yes_radio_button);
        r2 = (RadioButton) findViewById(R.id.no_radio_button);
        // This is used to find which button is used(we we can use either radio button or radio group.
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.myradio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb = (RadioButton) findViewById(checkedId);
                //Here we check if YES radio button is used.
                if (rb.getText().equals("Yes")) {
                    se2++;  //se2 is the variable used to update the score for 2nd question.
                }
            }
        });
      //Third question uses a Edit text box, this accepts the answer from the user, t3 is a variable used to update the score,
      // answerField3 is of type EditText.
        t3 = 0;
        answerField3 = (EditText) findViewById(R.id.answer_third);
        // Fourth question uses buttons, we use setOnClickListner to find out which method is used and as soon as we use this an
        // onclick method is automatically generated..
        f4 = 0;
        answerbutton4 = (Button) findViewById(R.id.undefine4);
        answerbutton5 = (Button) findViewById(R.id.Define4);
        answerbutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                f4++;
                Toast.makeText(getApplicationContext(), "You clicked UNDEFINED", Toast.LENGTH_SHORT).show();
            }
        });
        answerbutton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                wrongSol--;
                Toast.makeText(getApplicationContext(), "You clicked DEFINED", Toast.LENGTH_SHORT).show();
            }

        });
        // Please read the comments for 3rd questions
        answerField5 = (EditText) findViewById(R.id.answer_fifth);
//-------------------------------------------------------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //x.displayScore(View finalScore);
        //Toast.makeText(getApplicationContext(), "Your Score is " + finalScore,  Toast.LENGTH_SHORT).show();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    // This method is called when Submit button is called
    public void displayScore(View v) {
        // ( BASICALLY WE HAVE TO USE ALL THE ONCLICK LIKE(EDITTEXT, RADIOBUTTON,CHECKBOX) IN THE ONCREATE METHOD ONLY,AND
        //THERE REFERENCES ARE MADE USED IN OTHER METHOD AS WE HAVE DONE HERE
        // First question: This is the continuation for the first question code
        temp_opt1 = opt1.isChecked();
        temp_opt2 = opt2.isChecked();
        temp_opt3 = opt3.isChecked();
        if (temp_opt2) {
            f1 = 0;
        } else if ((temp_opt1 && temp_opt3)) {
            f1++;
        } else {
            wrongSol--;
        }
        // Third QUESTION : here we have to use the ".getText()" here as nothing should be displayed on the edit text as we refresh
        nameEditable3 = answerField3.getText();
        answer3 = nameEditable3.toString();
        s1 = "729";
        if (s1.equals(answer3)) {
            t3++;
        } else {
            wrongSol--;
        }
// Code for 5th solution: Same as the third question
        nameEditable5 = answerField5.getText();
        answer5 = nameEditable5.toString();
        s2 = "9";
        if (s2.equals(answer5)) {
            f5++;
        } else {
            wrongSol--;
        }
        finalScore = f1 + se2 + t3 + f4 + f5;
// I have made use of a counter called submit.
        if(submit==0){
            Toast.makeText(getApplicationContext(), "Your Score is: " + finalScore, Toast.LENGTH_SHORT).show();
            submit++;
        }
        else if(submit!=0){
            finalScore = 0;
            Toast.makeText(getApplicationContext(), "Please press RESTART button and proceed again, Score is: " + finalScore, Toast.LENGTH_SHORT).show();
        }
    }
// This is the reset method, I have used all the references of all the input types and set them to false.
    public void quizReset(View v) {
        submit=0;
        //First question:  Checkbox reset
        opt1.setChecked(false);
        opt2.setChecked(false);
        opt3.setChecked(false);
        // Second question : Radiobox reset
        r1.setChecked(false);
        r2.setChecked(false);
        //Third question: setting the string variable to null
        answer3 = null;
        answerField3.setText("");
        // Fifth question:
        answer5 = null;
        answerField5.setText("");
        // Reset all the golbal varables
        f1 = 0;
        se2 = 0;
        t3 = 0;
        f4 = 0;
        f5 = 0;
        finalScore = 0;
        // Below lines of code can be used to restart the activity
        /*Intent intent = getIntent();
        finish();
        startActivity(intent);*/
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
    @Override
    public void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.myquizz/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }
    @Override
    public void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.myquizz/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}















