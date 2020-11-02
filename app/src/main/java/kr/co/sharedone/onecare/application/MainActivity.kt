package kr.co.sharedone.onecare.application

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kr.co.sharedone.onecare.R
import kr.co.sharedone.onecare.ui.login.LoginViewModel

private const val TAG = "onecare-MainActivity"

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginViewModel by viewModels<LoginViewModel>()
        Log.d(TAG, "onCreate: ${loginViewModel.loginResult.toString()}")

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        navController.navigate(R.id.navigation_login)
//        navView.hide()
    }
}