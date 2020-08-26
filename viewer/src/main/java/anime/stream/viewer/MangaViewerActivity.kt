package anime.stream.viewer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import anime.stream.viewer.di.injector
import anime.stream.viewer.viewmodels.MangaViewerViewModel

class MangaViewerActivity : AppCompatActivity() {

    private val vm by lazy {
        injector.viewModel.create(this, getUriBundle()).create(MangaViewerViewModel::class.java)
    }

    private fun getUriBundle(): Bundle? {
        val bundle = Bundle()
        bundle.putString("mangaId", parseIntentData())
        return bundle
    }

    private fun parseIntentData(): String? {
        val data = intent?.data ?: return null

        return when (data.host) {
            "magadex.org" -> {
                val path = data.path ?: return null
                path.subSequence(6, path.length).toString()
            }
            "open.manga" -> {
                data.path
            }
            else -> null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val action = intent?.action
        val data = intent?.data

        if (checkActionAndData(action, data)) {
            Toast.makeText(this, "Illegal access", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        vm.manga.observe(this) {
            // todo Recycler view and adapter
        }
    }

    private fun checkActionAndData(action: String?, data: Uri?): Boolean {
        if (action == null || data == null) return true
        if (data.host == null) return true
        if (action != Intent.ACTION_VIEW) return true
        if (data.host != "open.manga" || data.host != "magadex.org") return true
        return false
    }
}