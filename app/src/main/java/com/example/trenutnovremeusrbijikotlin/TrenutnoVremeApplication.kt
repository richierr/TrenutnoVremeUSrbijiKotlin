package com.example.trenutnovremeusrbijikotlin

import android.app.Application
import android.content.Context
import com.pluto.Pluto
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.layoutinspector.PlutoLayoutInspectorPlugin
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.preferences.PlutoSharePreferencesPlugin
import com.pluto.plugins.rooms.db.PlutoRoomsDBWatcher
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin

class TrenutnoVremeApplication : Application() {

    override fun onCreate() {
        instance = this
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin())
            .addPlugin(PlutoExceptionsPlugin())
            .addPlugin(PlutoLoggerPlugin())
            .addPlugin(PlutoSharePreferencesPlugin())
            .addPlugin(PlutoRoomsDatabasePlugin())
            .addPlugin(PlutoLayoutInspectorPlugin())
            .install()
        AppDatabase.getDataBase(applicationContext)
        // DB_NAME should be same as database name assigned while creating the database.
        PlutoRoomsDBWatcher.watch("app_database", AppDatabase::class.java)
        super.onCreate()
    }

    companion object {
        private var instance: TrenutnoVremeApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }
}