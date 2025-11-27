package com.app.examenmobilesa01707647.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CovidPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("covid_prefs", Context.MODE_PRIVATE)

    fun saveLastCountry(country: String) {
        prefs.edit().putString("last_country", country).apply()
    }

    fun getLastCountry(): String {
        return prefs.getString("last_country", "Mexico") ?: "Mexico"
    }
}