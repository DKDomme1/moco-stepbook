package com.example.stepbook

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences

class DataStore(val context: Context) {
    //val Context.datastore:DataStore<Preferences> by preferencesDataStore(name="Stepcount")
}