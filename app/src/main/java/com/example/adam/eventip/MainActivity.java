package com.example.adam.eventip;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity {

    private EditText billAmount,totalPlusTip;
    private TextView tipPercent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        billAmount = (EditText) findViewById(R.id.bill);



        totalPlusTip= (EditText)findViewById(R.id.totalPlusTip);

        totalPlusTip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {


                hideKeyboard();
                    double total = Double.parseDouble(billAmount.getText().toString());
                   double tippedTotal = Double.parseDouble(totalPlusTip.getText().toString());
                    tipCalc(total,tippedTotal);

                    return true;
                }
                return false;
            }
        });




        tipPercent=(TextView)findViewById(R.id.tipPercent);

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



    public void tipCalc(double total, double tippedTotal) {
        double percentage = (( tippedTotal/total) - 1) * 100;

        int endPercent = (int) percentage;

        if (endPercent >= 15) {
            tipPercent.setTextColor(Color.GREEN);
        } else if (endPercent <15 && endPercent >= 12) {
            tipPercent.setTextColor(Color.YELLOW);
        } else {
            tipPercent.setTextColor(Color.RED);
        }

        tipPercent.setText(endPercent + "%");
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
