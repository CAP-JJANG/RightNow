package com.capjjang.rightnow.ui

import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseFragment
import com.capjjang.rightnow.databinding.FragmentQuizGradeBinding
import com.capjjang.rightnow.util.MyGlobals


class QuizGradeFragment: BaseFragment<FragmentQuizGradeBinding>(R.layout.fragment_quiz_grade){


    override fun initAfterBinding() {
        super.initAfterBinding()

        val grade: String? = MyGlobals.instance?.data
        binding.tvGrade.text = "당신의 점수는 $grade 점입니다"

        binding.button2.setOnClickListener {
            navController.navigate(R.id.action_quizGradeFragment2_to_startFragment)
        }
    }
}
