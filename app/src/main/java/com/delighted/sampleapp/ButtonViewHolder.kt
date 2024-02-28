package com.delighted.sampleapp

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.delighted.databinding.ViewSurveyButtonBinding

class ButtonViewHolder(
    private val viewBinding: ViewSurveyButtonBinding,
    private val callback: (String) -> Unit
) :
    ViewHolder(viewBinding.root) {

    fun bind(buttonName: String, delightedId: String) {
        viewBinding.triggerButton.text = buttonName
        viewBinding.triggerButton.setOnClickListener {
            callback(delightedId)
        }
    }
}
