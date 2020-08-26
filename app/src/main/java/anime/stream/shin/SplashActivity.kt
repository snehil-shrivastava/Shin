package anime.stream.shin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ActivityNavigator

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch()
    }

    private fun launch() {
        val mIntent = Intent(this, MainActivity::class.java)
        mIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        ActivityNavigator(this).navigate(
            ActivityNavigator(this).createDestination().setIntent(mIntent),
            null, null, null
        )
        this.finish()
    }
}
