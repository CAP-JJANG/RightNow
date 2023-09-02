package com.capjjang.rightnow.ui

import android.content.Context
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseBottomDialogFragment
import com.capjjang.rightnow.databinding.DialogQuizResultBinding

class QuizResultDialog(val answer: String): BaseBottomDialogFragment<DialogQuizResultBinding>(R.layout.dialog_quiz_result) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()

        binding.button.setOnClickListener {
            dismiss()
            navController.navigate(R.id.action_quizFragment_to_quizGradeFragment2)
        }

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val myAnswer = sharedPref.getString("myAnswer", "")
        var grade = sharedPref.getInt("grade",0)

        when (answer) {
            "cat" -> {
                Log.d("grade",grade.toString())
                binding.tvHansung.visibility = View.INVISIBLE
                if(myAnswer == "cat"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 25)
                        apply()
                    }
                }
                else {
                    binding.tvCheck.text = "오답입니다!"
                }
                binding.tvQuiz.text="고양이는 영어로 cat 입니다."
            }
            "dog" -> {
                Log.d("grade",grade.toString())
                binding.tvHansung.visibility = View.INVISIBLE
                if(myAnswer == "dog"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 25)
                        apply()
                    }
                }

                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="강아지는 영어로 dog 입니다."
            }
            "elephant"->{
                Log.d("grade",grade.toString())
                binding.tvHansung.visibility = View.INVISIBLE
                if(myAnswer == "elephant"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 25)
                        apply()
                    }
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="코끼리는 영어로 elephant 입니다"
            }
            "hansung"->{
                Log.d("grade",grade.toString())
                binding.button.visibility = View.VISIBLE
                if(myAnswer == "hansung"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 25)
                        apply()
                    }
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="이 로고의 학교는 hansung 입니다."
            }

        }

        binding.imageButton2.setOnClickListener{
            dismiss()
        }

    }
}