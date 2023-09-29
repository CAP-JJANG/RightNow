package com.capjjang.rightnow.ui

import android.content.Context
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseBottomDialogFragment
import com.capjjang.rightnow.databinding.DialogQuizResultBinding

class QuizResultDialog(val answer: Int, val isAnswerCorrect : ArrayList<Boolean>): BaseBottomDialogFragment<DialogQuizResultBinding>(R.layout.dialog_quiz_result) {


    private val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)

    var grade = sharedPref?.getInt("grade",0)


    val englishAnswerList = arrayListOf("elephant","chick","frog","bear","rabbit")
    val koreanAnswerList = arrayListOf("꼬끼리","병아리","개구리","곰","토끼")


    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()

        binding.ResultButton.setOnClickListener {
            dismiss()
            navController.navigate(R.id.action_quizFragment_to_quizGradeFragment2)
        }

        checkAnswer(answer)

        binding.imageButton2.setOnClickListener{
            dismiss()
        }

    }

    private fun checkAnswer(index : Int){
        if(isAnswerCorrect[index]){
            binding.tvCheck.text ="정답입니다!"
            with (sharedPref!!.edit()) {
                putInt("grade", grade?.plus(25) ?: 25)
                apply()
            }
        }
        if(index == 4){
            binding.ResultButton.visibility=View.VISIBLE
        }

        else
            binding.tvCheck.text ="오답입니다!"
        binding.tvQuiz.text="${koreanAnswerList[index]} 영어로 ${englishAnswerList[index]} 입니다."
    }
}