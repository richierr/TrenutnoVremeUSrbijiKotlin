package com.example.trenutnovremeusrbijikotlin

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.trenutnovremeusrbijikotlin.network.AutomaticSyncManager

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        var syncPreference: SwitchPreferenceCompat? =findPreference("sync")
        syncPreference?.onPreferenceChangeListener = object: OnPreferenceChangeListener{
            override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
                newValue?.let {
                    if(it as Boolean){
                        AutomaticSyncManager.scheduleSync(15)
                    }else{
                        AutomaticSyncManager.cancelAllSync()
                    }
                }
                return true
            }
        }

    }


}