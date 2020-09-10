package ng.edu.ibbu.gadsleaderboard.screens.main.skilliq

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_first.*
import ng.edu.ibbu.gadsleaderboard.R

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class SkillIQFragment : Fragment(R.layout.fragment_second) {

    private val viewModel: SkillIQFragmentViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Skilliqadapter()
        val layoutManager = LinearLayoutManager(context)

        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


        viewModel.fetchLearnersBySkillIQ()
        viewModel.topLearners.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }

}