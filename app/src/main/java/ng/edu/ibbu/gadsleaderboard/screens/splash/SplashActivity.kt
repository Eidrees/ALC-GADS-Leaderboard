package ng.edu.ibbu.gadsleaderboard.screens.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import ng.edu.ibbu.gadsleaderboard.R
import ng.edu.ibbu.gadsleaderboard.screens.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            },
            2000
        )

    }
}