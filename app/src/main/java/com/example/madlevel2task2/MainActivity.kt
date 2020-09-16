package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<QuestionModel>()
    private val questionAdapter = QuestionAdapter(questions)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.rvQuestions.layoutManager =
                LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.addItemDecoration(
                DividerItemDecoration(
                        this@MainActivity,
                        DividerItemDecoration.VERTICAL
                )
        )
        binding.rvQuestions.adapter = questionAdapter

        for (i in QuestionModel.QUESTION_NAMES.indices) {
            questions.add(
                    QuestionModel(
                            QuestionModel.QUESTION_NAMES[i],
                            QuestionModel.QUESTION_STATUS[i]
                    )
            )
        }

        questionAdapter.notifyDataSetChanged()
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun showError() {
        Snackbar.make(rvQuestions, getString(R.string.error_message), Snackbar.LENGTH_SHORT).show()
    }

    private fun validateAnswer(questionIndex: Int, expectedResponse: Boolean) {
        val answer = questions[questionIndex].questionBoolean

        if (answer == expectedResponse) questions.removeAt(questionIndex)
        else showError()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when (direction) {
                    //Remove question in case answer is true
                    ItemTouchHelper.RIGHT -> validateAnswer(viewHolder.adapterPosition, true)

                    //Remove question in case answer is false
                    ItemTouchHelper.LEFT -> validateAnswer(viewHolder.adapterPosition, false)
                }
                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}