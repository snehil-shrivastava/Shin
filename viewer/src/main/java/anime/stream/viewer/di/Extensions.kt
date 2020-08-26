package anime.stream.viewer.di

import androidx.appcompat.app.AppCompatActivity

val AppCompatActivity.injector
    get() = (application as ViewerComponentProvider).viewerComponent
