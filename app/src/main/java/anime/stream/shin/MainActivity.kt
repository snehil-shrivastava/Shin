package anime.stream.shin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.fxn.OnBubbleClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindChipNavBarWithBottomNavigationView()
        setupNavBar()
    }

    private fun setupNavBar() {
        val navController: NavController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupWithNavController(navBar, navController)
        chipNavBar.addBubbleListener(object : OnBubbleClickListener {
            override fun onBubbleClick(id: Int) {
                if (navController.currentDestination?.id != id)
                    navBar.selectedItemId = id
            }
        })
    }

    private fun bindChipNavBarWithBottomNavigationView() {

    }
}

