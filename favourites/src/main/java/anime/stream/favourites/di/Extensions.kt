package anime.stream.favourites.di

import androidx.fragment.app.Fragment

val Fragment.injector
    get() = (
            this.requireActivity().application as FavouriteComponentProvider).favouriteComponent
