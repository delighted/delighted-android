package com.delighted.sampleapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.delighted.databinding.ViewSurveyButtonBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class SurveyButtonsAdapter : Adapter<ButtonViewHolder>() {
    private val _triggerId = MutableSharedFlow<String>(replay = 1)
    val triggerId: Flow<String> get() = _triggerId

    private val surveys = mutableListOf<Pair<String, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        return ButtonViewHolder(
            viewBinding = ViewSurveyButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            callback = { id -> _triggerId.tryEmit(id) }
        )
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(surveys[position].first, surveys[position].second)
    }

    override fun getItemCount(): Int = surveys.size

    fun submitList(list: List<Pair<String, String>>) {
        surveys.clear()
        surveys.addAll(list)
        notifyDataSetChanged()
    }
}
