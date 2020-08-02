package anime.stream.shin.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [AppModule::class]
)
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}