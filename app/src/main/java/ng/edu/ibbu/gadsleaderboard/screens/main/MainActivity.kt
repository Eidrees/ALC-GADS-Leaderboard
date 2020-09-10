package ng.edu.ibbu.gadsleaderboard.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import ng.edu.ibbu.gadsleaderboard.R
import ng.edu.ibbu.gadsleaderboard.screens.main.learners.LearnerFragment
import ng.edu.ibbu.gadsleaderboard.screens.main.skilliq.SkillIQFragment
import ng.edu.ibbu.gadsleaderboard.screens.submission.SubmitActivity

private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {

    private lateinit var view_pager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        tvSubmit.setOnClickListener {
            startActivity(Intent(this, SubmitActivity::class.java));
        }

        view_pager = viewPager
        val pagerAdapter = PagerAdapter(this)
        view_pager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = (if (position == 0) getString(R.string.learning_leaders) else getString(R.string.skill_iq_leaders))
        }.attach()

    }


    override fun onBackPressed() {
        if (view_pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            view_pager.currentItem = view_pager.currentItem - 1
        }
    }

    private inner class PagerAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val fragment = listOf(
            LearnerFragment(),
            SkillIQFragment()
        )

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return fragment[position]
        }

    }

}