package com.capjjang.rightnow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capjjang.rightnow.databinding.QuizItemBinding
import kotlin.collections.ArrayList

class QuizAdapter(
    var list: ArrayList<Int>
) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {


    inner class ViewHolder(itemViewBinding: QuizItemBinding)
        : RecyclerView.ViewHolder(itemViewBinding.root){
        var img = itemViewBinding.ivQuizImg
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            QuizItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.img.setImageResource(list[position])

    }
}