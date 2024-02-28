package com.delighted.sdk.view.fragment

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.delighted.sdk.databinding.ViewSelectAqButtonBinding
import com.delighted.sdk.view.theme.ThemeApply

class SelectableViewHolder(
    private val viewBinding: ViewSelectAqButtonBinding,
    private val themeApply: ThemeApply,
) : ViewHolder(viewBinding.root) {

    fun bind(
        id: String,
        labelText: String,
        isSelected: Boolean,
        listener: (id: String, isChecked: Boolean) -> Unit
    ) {
        viewBinding.selectableButton.text = labelText
        viewBinding.selectableButton.isChecked = isSelected
        viewBinding.selectableButton.setOnCheckedChangeListener { button, isChecked ->
            if (button.isPressed) listener(id, isChecked)
        }
        themeApply.applyAqSelectableButton(
            button = viewBinding.selectableButton,
            resources = itemView.resources
        )
    }
}
