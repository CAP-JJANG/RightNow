package com.capjjang.rightnow.ui

import android.content.Context
import android.view.View
import androidx.navigation.fragment.navArgs
import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseBottomDialogFragment
import com.capjjang.rightnow.databinding.DialogQuizResultBinding

class QuizResultDialog: BaseBottomDialogFragment<DialogQuizResultBinding>(R.layout.dialog_quiz_result) {
    val args: QuizResultDialogArgs by navArgs()

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


        binding.button.setOnClickListener {
            navController.navigate(R.id.action_quizResultDialog_to_quizGradeFragment2)
        }
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val answer = sharedPref.getString("myAnswer", "")
        var grade = sharedPref.getInt("grade",0)

        when (answer) {
            "cat" -> {
                binding.tvHansung.visibility = View.INVISIBLE
                if(answer == "cat"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 20)
                        apply()
                    }
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="고양이는 영어로 cat 입니다."
            }
            "dog" -> {
                binding.tvHansung.visibility = View.INVISIBLE
                if(answer == "dog"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 20)
                        apply()
                    }
                }

                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="강아지는 영어로 dog 입니다."
            }
            "elephant"->{
                binding.tvHansung.visibility = View.INVISIBLE
                if(answer == "elephant"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 20)
                        apply()
                    }
                }
                else
                    binding.tvCheck.text ="오답입니다!"
                binding.tvQuiz.text="코끼리는 영어로 elephant 입니다"
            }
            "hansung"->{
                binding.tvHansung.visibility = View.VISIBLE
                if(answer == "hansung"){
                    binding.tvCheck.text ="정답입니다!"
                    with (sharedPref!!.edit()) {
                        putInt("grade", grade + 20)
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