package ng.edu.ibbu.gadsleaderboard.screens.main.learners

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ng.edu.ibbu.gadsleaderboard.databinding.LayoutItemsBinding
import ng.edu.ibbu.gadsleaderboard.network.Learner

class LeanerAdapter : ListAdapter<Learner, LeanerAdapter.ViewHolder>(AdapterDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: LayoutItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(learner: Learner) {
            binding.learner = learner
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemsBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }
    }

    class AdapterDiffUtill : DiffUtil.ItemCallback<Learner>() {
        override fun areItemsTheSame(oldItem: Learner, newItem: Learner): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Learner, newItem: Learner): Boolean {
            return oldItem == newItem
        }
    }


}