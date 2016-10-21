package us.team.awesome.calculator.activities;

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

import us.team.awesome.calculator.R;
import us.team.awesome.calculator.components.EquationView;
import us.team.awesome.calculator.math.CalculationList;
import us.team.awesome.calculator.util.EquationMalformedException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton;
    private Button sevenButton, eightButton, nineButton, addButton, substractButton, collonButton;
    private Button clearButton, multiplyButton, divideButton;
    private EquationView equationView;

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

        this.equationView = (EquationView) findViewById(R.id.equationView);

        this.zeroButton = (Button) findViewById(R.id.zeroButton);
        this.oneButton = (Button) findViewById(R.id.oneButton);
        this.twoButton = (Button) findViewById(R.id.twoButton);
        this.threeButton = (Button) findViewById(R.id.threeButton);
        this.fourButton = (Button) findViewById(R.id.fourButton);
        this.fiveButton = (Button) findViewById(R.id.fiveButton);
        this.sixButton = (Button) findViewById(R.id.sixButton);
        this.sevenButton = (Button) findViewById(R.id.sevenButton);
        this.eightButton = (Button) findViewById(R.id.eightButton);
        this.nineButton = (Button) findViewById(R.id.nineButton);

        this.collonButton = (Button) findViewById(R.id.collonButton);

        this.addButton = (Button) findViewById(R.id.addButton);
        this.substractButton = (Button) findViewById(R.id.substractButton);
        this.multiplyButton = (Button) findViewById(R.id.multiplyButton);
        this.divideButton = (Button) findViewById(R.id.divideButton);

        this.clearButton = (Button) findViewById(R.id.clearButton);
        this.clearButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                equationView.clear();
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

    public void numberClickedListener(View view) {
        Button clickedButton = (Button) view;
        String number = (String) clickedButton.getText();
        equationView.addNumber(number);
    }

    public void multiplyClickedListener(View view) {
        equationView.addMultiplyOperator();
    }

    public void divideClickedListener(View view) {
        equationView.addDivideOperator();
    }

    public void addClickedListener(View view) {
        equationView.addAddOperator();
    }

    public void subtractClickedListener(View view) {
        equationView.addSubtractOperator();
    }

    public void colonClickedListener(View view) {
        equationView.addDecimalPoint();
    }

    public void calculateClickedListener(View view) {
        equationView.calculate();
    }

    public void bracketLeftClickedListener(View view) {
        equationView.addLeftBracket();
    }

    public void bracketRightClickedListener(View view) {
        equationView.addRightBracket();
    }

    private String getButtonSign(View view) {
        return (String) ((Button) view).getText();
    }
}