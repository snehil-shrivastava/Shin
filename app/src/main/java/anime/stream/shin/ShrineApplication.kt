package anime.stream.shin

import android.app.Activity
import android.app.Application
import anime.stream.shin.di.ApplicationComponent
import anime.stream.shin.di.DaggerApplicationComponent


class ShrineApplication : Application(), ComponentProvider {
    override val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

interface ComponentProvider {
    val component: ApplicationComponent
}

val Activity.injector get() = (application as ComponentProvider).component
