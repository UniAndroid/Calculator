package us.team.awesome.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView calculationTextView;
    private Button zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton;
    private Button sevenButton, eightButton, nineButton, addButton, substractButton, collonButton;
    private Button clearButton, multiplyButton, divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.calculationTextView = (TextView) findViewById(R.id.calculationTextView);

        this.zeroButton = (Button) findViewById(R.id.zeroButton);
        this.zeroButton.setOnClickListener(this.getNumberClickListener((String) this.zeroButton.getText()));
        this.oneButton = (Button) findViewById(R.id.oneButton);
        this.oneButton.setOnClickListener(this.getNumberClickListener((String) this.oneButton.getText()));
        this.twoButton = (Button) findViewById(R.id.twoButton);
        this.twoButton.setOnClickListener(this.getNumberClickListener((String) this.twoButton.getText()));
        this.threeButton = (Button) findViewById(R.id.threeButton);
        this.threeButton.setOnClickListener(this.getNumberClickListener((String) this.threeButton.getText()));
        this.fourButton = (Button) findViewById(R.id.fourButton);
        this.fourButton.setOnClickListener(this.getNumberClickListener((String) this.fourButton.getText()));
        this.fiveButton = (Button) findViewById(R.id.fiveButton);
        this.fiveButton.setOnClickListener(this.getNumberClickListener((String) this.fiveButton.getText()));
        this.sixButton = (Button) findViewById(R.id.sixButton);
        this.sixButton.setOnClickListener(this.getNumberClickListener((String) this.sixButton.getText()));
        this.sevenButton = (Button) findViewById(R.id.sevenButton);
        this.sevenButton.setOnClickListener(this.getNumberClickListener((String) this.sevenButton.getText()));
        this.eightButton = (Button) findViewById(R.id.eightButton);
        this.eightButton.setOnClickListener(this.getNumberClickListener((String) this.eightButton.getText()));
        this.nineButton = (Button) findViewById(R.id.nineButton);
        this.nineButton.setOnClickListener(this.getNumberClickListener((String) this.nineButton.getText()));

        this.collonButton = (Button) findViewById(R.id.collonButton);
        this.collonButton.setOnClickListener(this.getNumberClickListener((String) this.collonButton.getText()));

        this.addButton = (Button) findViewById(R.id.addButton);
        this.addButton.setOnClickListener(this.getOperatorClickListener((String) this.addButton.getText()));
        this.substractButton = (Button) findViewById(R.id.substractButton);
        this.substractButton.setOnClickListener(this.getOperatorClickListener((String) this.substractButton.getText()));
        this.multiplyButton = (Button) findViewById(R.id.multiplyButton);
        this.multiplyButton.setOnClickListener(this.getOperatorClickListener((String) this.multiplyButton.getText()));
        this.divideButton = (Button) findViewById(R.id.divideButton);
        this.divideButton.setOnClickListener(this.getOperatorClickListener((String) this.divideButton.getText()));

        this.clearButton = (Button) findViewById(R.id.clearButton);
        this.clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                calculationTextView.setText("0");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private View.OnClickListener getNumberClickListener(final String number){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calculationTextView.getText().length() == 1 && calculationTextView.getText().charAt(0) == '0'){
                    calculationTextView.setText(number);
                } else {
                    calculationTextView.setText(calculationTextView.getText() + number);
                }
            }
        };
    }

    private View.OnClickListener getOperatorClickListener(final String operator){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculationTextView.setText(calculationTextView.getText() + operator);
            }
        };
    }
}
