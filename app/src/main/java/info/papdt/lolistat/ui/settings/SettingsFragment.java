package info.papdt.lolistat.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import info.papdt.lolistat.R;
import info.papdt.lolistat.support.Settings;

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener
{
	
	private Settings mSettings;
	
	private CheckBoxPreference mTintNav;
	private CheckBoxPreference mTintIcons;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
		
		mSettings = Settings.getInstance(getActivity());
		
		mTintNav = (CheckBoxPreference) findPreference(Settings.PREF_TINT_NAVIGATION);
		mTintNav.setChecked(mSettings.getBoolean(Settings.PREF_TINT_NAVIGATION, true));
		mTintNav.setOnPreferenceChangeListener(this);
		
		mTintIcons = (CheckBoxPreference) findPreference(Settings.PREF_TINT_ICONS);
		mTintIcons.setChecked(mSettings.getBoolean(Settings.PREF_TINT_ICONS, true));
		mTintIcons.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceChange(Preference pref, Object newValue) {
		if (pref == mTintNav) {
			mSettings.putBoolean(Settings.PREF_TINT_NAVIGATION, Boolean.valueOf(newValue));
		} else if (pref == mTintIcons) {
			mSettings.putBoolean(Settings.PREF_TINT_ICONS, Boolean.valueOf(newValue));
		}
		return true;
	}

	@Override
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference.getKey().equals("blacklist")) {
			Intent i = new Intent();
			i.setAction(Intent.ACTION_MAIN);
			i.setClass(getActivity(), BlackListActivity.class);
			startActivity(i);
			return true;
		} else {
			return super.onPreferenceTreeClick(preferenceScreen, preference);
		}
	}
	
}