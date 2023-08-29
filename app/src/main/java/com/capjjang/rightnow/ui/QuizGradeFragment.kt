package com.capjjang.rightnow.ui

import android.content.Context
import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseFragment
import com.capjjang.rightnow.databinding.FragmentQuizGradeBinding

class QuizGradeFragment: BaseFragment<FragmentQuizGradeBinding>(R.layout.fragment_quiz_grade) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val grade = sharedPref.getInt("grade",0)

        binding.tvGrade.text = "당신의 점수는 $grade 점입니다"

    }

}