package us.team.awesome.calculator.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import us.team.awesome.calculator.R;

/**
 * Created by Stefan on 29.10.2016.
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
