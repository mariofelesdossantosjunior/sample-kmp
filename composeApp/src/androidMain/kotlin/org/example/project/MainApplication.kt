package org.example.project

import android.app.Application
import com.expenseApp.db.AppDatabase
import data.DatabaseDriverFactory
import di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(
                appModules(
                    AppDatabase(
                        DatabaseDriverFactory(
                            this@MainApplication
                        ).createDriver()
                    )
                )
            )
        }
    }
}