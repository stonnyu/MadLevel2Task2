package com.example.madlevel2task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ItemQuestionBinding

class QuestionAdapter(private val questionModels: List<QuestionModel>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemQuestionBinding.bind(itemView)

        fun databind(questionModel: QuestionModel) {
            binding.tvQuestion.text = questionModel.questionName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questionModels.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(questionModels[position])
    }

}