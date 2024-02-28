package com.delighted.sdk.view.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.delighted.sdk.databinding.ViewSelectAqButtonBinding
import com.delighted.sdk.view.theme.ThemeApply
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class SelectableAdapter(
    private val isSelectOne: Boolean,
    private val themeApply: ThemeApply,
) : Adapter<SelectableViewHolder>() {

    private var recyclerView: RecyclerView? = null
    private lateinit var items: List<SelectableItem>

    private val _answerData = MutableSharedFlow<List<String>>(replay = 1)
    val answerData: SharedFlow<List<String>> get() = _answerData

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        this.recyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectableViewHolder {
        return SelectableViewHolder(
            viewBinding = ViewSelectAqButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            themeApply = themeApply
        )
    }

    override fun onBindViewHolder(holder: SelectableViewHolder, position: Int) {
        val item = items[position]
        holder.bind(
            id = item.id,
            labelText = item.labelText,
            isSelected = item.isSelected,
            listener = listener
        )
    }

    override fun getItemCount(): Int = items.size

    fun submitList(items: List<SelectableItem>) {
        this.items = items
        // init button states with all button ids and set to false
        notifyDataSetChanged()
    }

    data class SelectableItem(val id: String, val labelText: String, var isSelected: Boolean)

    private val listener: (String, Boolean) -> Unit = { id: String, isSelected: Boolean ->
        val item = items.find { it.id == id }
        item?.isSelected = isSelected
        if (isSelectOne) {
            for (i in items - item) {
                i?.isSelected = false
            }
        }
        recyclerView?.post { notifyDataSetChanged() }
        // emit list of only id's that are selected (true)
        _answerData.tryEmit(items.filter { it.isSelected }.map { it.id })
    }
}
