package anime.stream.core.utils

import android.content.Intent
import android.net.Uri

object IntentUtility {
    fun getMangaIntent(mangaId: String): Intent {
        val mIntent = Intent()
        mIntent.action = Intent.ACTION_VIEW
        mIntent.data = Uri.parse("app://open.manga/$mangaId")
        return mIntent
    }
}