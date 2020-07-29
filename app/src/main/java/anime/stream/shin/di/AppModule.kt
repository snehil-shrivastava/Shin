package anime.stream.shin.di

import androidx.lifecycle.ViewModel
import anime.stream.core.di.AssistedSavedStateViewModelFactory
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Module
import dagger.multibindings.Multibinds

@AssistedModule
@Module(includes = [AssistedInject_AppModule::class])
abstract class AppModule {

    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>
}
