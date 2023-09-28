package com.example.trenutnovremeusrbijikotlin

import android.app.Application
import android.util.Log
import com.pluto.Pluto
import com.pluto.plugins.exceptions.PlutoExceptionsPlugin
import com.pluto.plugins.layoutinspector.PlutoLayoutInspectorPlugin
import com.pluto.plugins.logger.PlutoLoggerPlugin
import com.pluto.plugins.network.PlutoNetworkPlugin
import com.pluto.plugins.preferences.PlutoSharePreferencesPlugin
import com.pluto.plugins.rooms.db.PlutoRoomsDatabasePlugin

class TrenutnoVremeApplication : Application() {
    override fun onCreate() {
        Pluto.Installer(this)
            .addPlugin(PlutoNetworkPlugin())
            .addPlugin(PlutoExceptionsPlugin())
            .addPlugin(PlutoLoggerPlugin())
            .addPlugin(PlutoSharePreferencesPlugin())
            .addPlugin(PlutoRoomsDatabasePlugin())
            .addPlugin(PlutoLayoutInspectorPlugin())
        .install()
        super.onCreate()
    }
}