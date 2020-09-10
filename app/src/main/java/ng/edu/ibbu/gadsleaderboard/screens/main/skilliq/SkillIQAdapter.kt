package ng.edu.ibbu.gadsleaderboard.screens.main.skilliq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ng.edu.ibbu.gadsleaderboard.databinding.LayoutItemsSkilledBinding
import ng.edu.ibbu.gadsleaderboard.network.LearnerSkillIQ

class Skilliqadapter : ListAdapter<LearnerSkillIQ, Skilliqadapter.ViewHolder>(AdapterDiffUtill()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder private constructor(val binding: LayoutItemsSkilledBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(skillIQ: LearnerSkillIQ) {
            binding.skilliq = skillIQ
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutItemsSkilledBinding.inflate(layoutInflater)
                return ViewHolder(binding)
            }
        }
    }

    class AdapterDiffUtill : DiffUtil.ItemCallback<LearnerSkillIQ>() {
        override fun areItemsTheSame(oldItem: LearnerSkillIQ, newItem: LearnerSkillIQ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LearnerSkillIQ, newItem: LearnerSkillIQ): Boolean {
            return oldItem == newItem
        }
    }


}