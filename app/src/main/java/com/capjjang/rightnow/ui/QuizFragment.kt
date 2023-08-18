package com.capjjang.rightnow.ui

import androidx.viewpager2.widget.ViewPager2
import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseFragment
import com.capjjang.rightnow.databinding.FragmentQuizBinding

class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {


    override fun initStartView() {
        super.initStartView()
    }

    override fun initDataBinding() {
        super.initDataBinding()


        // 어댑터 생성
        binding.viewPager2.adapter = QuizAdapter(getCardList())
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        val dpValue = 40
        val d = resources.displayMetrics.density
        val margin = (dpValue * d).toInt()

        binding.viewPager2.clipToPadding = false
        binding.viewPager2.setPadding(margin, 0, margin, 0)

        binding.dotsIndicator.setViewPager2(binding.viewPager2)
    }


    override fun initAfterBinding() {
        super.initAfterBinding()

    }


    private fun getCardList(): ArrayList<Int> {
        return arrayListOf<Int>(
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.elephant,
            R.drawable.hansung
        )
    }



}