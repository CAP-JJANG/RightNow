package com.capjjang.rightnow.ui

import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseFragment
import com.capjjang.rightnow.databinding.FragmentQuizGradeBinding
import com.capjjang.rightnow.util.MyApplication

class QuizGradeFragment: BaseFragment<FragmentQuizGradeBinding>(R.layout.fragment_quiz_grade) {

    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()


        val grade = MyApplication.prefs.getString("grade","00")

        binding.tvGrade.text = "당신의 점수는 $grade 점입니다"

    }

}