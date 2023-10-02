package com.example.trenutnovremeusrbijikotlin.fragments

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.trenutnovremeusrbijikotlin.R
import com.example.trenutnovremeusrbijikotlin.SharedPlacesViewModel

class SettingsFragment : PreferenceFragmentCompat() {
    lateinit var mViewModel: SharedPlacesViewModel

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        activity?.let { mViewModel = ViewModelProvider(it).get(SharedPlacesViewModel::class.java) }
        var syncPreference: SwitchPreferenceCompat? =findPreference("sync")
        syncPreference?.onPreferenceChangeListener = object: OnPreferenceChangeListener{
            override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
                newValue?.let {
                    if(it as Boolean){
                        mViewModel.autoSyncData(15)
                    }else{
                        mViewModel.cancelAutoSyncData()
                    }
                }
                return true
            }
        }

    }


}