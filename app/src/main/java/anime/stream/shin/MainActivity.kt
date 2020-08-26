package anime.stream.shin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.fxn.OnBubbleClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val listener = NavController
        .OnDestinationChangedListener { _, destination, _ ->
            chipNavBar.setSelected(getItemPositionById(destination.id), false)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupNavBar()
    }

    private fun setupNavBar() {
        navController = findNavController(R.id.fragment)
        NavigationUI.setupWithNavController(navBar, navController)
        chipNavBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                if (navController.currentDestination?.id != id)
                    navBar.selectedItemId = id
            }
        })
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        navController.removeOnDestinationChangedListener(listener)
        super.onPause()
    }

    private fun getItemPositionById(id: Int): Int {
        val pos = when (id) {
            R.id.menu_nav_bar_home -> 0
            R.id.menu_nav_bar_search -> 1
            R.id.menu_nav_bar_favourites -> 2
            else -> 0
        }
        return pos
    }

}

