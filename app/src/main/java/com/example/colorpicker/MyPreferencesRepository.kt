package com.example.colorpicker

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import java.io.File
import java.util.prefs.Preferences

class MyPreferencesRepository private constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        private const val PREFERENCES_DATA_FILE_NAME = "settings"

        private var INSTANCE: MyPreferencesRepository? = null

        fun initialize(context: Context) {

            if ( INSTANCE == null) {
                    val file = File(context.filesDir, PREFERENCES_DATA_FILE_NAME)

            }

        }
    }
}