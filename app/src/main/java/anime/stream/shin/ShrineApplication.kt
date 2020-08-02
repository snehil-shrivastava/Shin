package anime.stream.shin

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.annotation.NonNull
import anime.stream.core.services.FavouriteService
import anime.stream.core.services.MangaNetworkService
import anime.stream.favourites.di.DaggerFavouriteComponent
import anime.stream.favourites.di.FavouriteComponent
import anime.stream.favourites.di.FavouriteComponentProvider
import anime.stream.network.di.DaggerNetworkComponent
import anime.stream.network.di.NetworkComponent
import anime.stream.shin.di.ApplicationComponent
import anime.stream.shin.di.ComponentProvider
import anime.stream.shin.di.DaggerApplicationComponent
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber
import timber.log.Timber.DebugTree

class ShrineApplication : Application(), ComponentProvider, FavouriteComponentProvider {

    override val favouriteComponent: FavouriteComponent by lazy {
        DaggerFavouriteComponent.factory().create(networkService, this)
    }

    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    private val networkComponent: NetworkComponent by lazy {
        DaggerNetworkComponent.factory().create()
    }

    // Services
    private val favouriteService: FavouriteService by lazy { favouriteComponent.serviceImpl }
    private val networkService: MangaNetworkService by lazy { networkComponent.debugMangaDexProvider }

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }


    /** A tree which logs important information for crash reporting.  */
    private class CrashReportingTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, @NonNull message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return
            }
            // TODO : FakeCrashLibrary.log(priority, tag, message)
            if (t != null) {
                if (priority == Log.ERROR) {
                    // TODO : FakeCrashLibrary.logError(t)
                } else if (priority == Log.WARN) {
                    // TODO : FakeCrashLibrary.logWarning(t)
                }
            }
        }
    }

}


val Activity.injector get() = (application as ComponentProvider).component
