package us.team.awesome.calculator.activities;

import android.app.Activity;
import android.os.Bundle;

import us.team.awesome.calculator.preferences.SettingsFragment;

/**
 * Created by Stefan on 29.10.2016.
 */

public class SettingsActivity extends Activity {
    public static final String AUTO_CALCULATE = "autoCalculate";
    public static final String CUSTOM_DRAWER = "customDrawer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
