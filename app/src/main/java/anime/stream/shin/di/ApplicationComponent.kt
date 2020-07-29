package anime.stream.shin.di

import android.app.Application
import anime.stream.core.di.InjectingSavedStateViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Application): ApplicationComponent
    }

    val abstractViewModelFactory: InjectingSavedStateViewModelFactory

}