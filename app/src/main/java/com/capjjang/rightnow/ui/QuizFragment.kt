package com.capjjang.rightnow.ui

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseFragment
import com.capjjang.rightnow.databinding.FragmentQuizBinding
import com.capjjang.rightnow.util.AudioRecorder
import com.example.rightnow.apiManager.RecordApiManager
import java.util.Date


class QuizFragment : BaseFragment<FragmentQuizBinding>(R.layout.fragment_quiz) {

    var quizItems = arrayListOf<String>("cat","dog","elephant","hansung")
    var answer = ""

    val apiManager = RecordApiManager.getInstance(context)

    val audioRecorder = AudioRecorder()

    private val isAnswerCorrect = arrayListOf<Boolean>(false,false,false,false)
    val isAnswerSubmitted = arrayListOf<Boolean>(false,false,false,false)


    override fun initStartView() {
        super.initStartView()


        val sharedPref = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)

        with (sharedPref!!.edit()) {
            putInt("grade", 0)
            apply()
        }
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


        // 알파벳 값이 변경될 때마다 호출
        apiManager?.resultLivedata?.observe(viewLifecycleOwner) { newValue ->
            binding.tvText.visibility = View.INVISIBLE
            binding.btnBack.visibility = View.VISIBLE

            if(audioRecorder.isRecording == true){
                // 화면에 값 변경
                binding.textView2.text = binding.textView2.text.toString()+ newValue
                Log.d("[mmihye]", "값 $newValue 으로 변경")

                // UI 업데이트 후 녹음 시작
                binding.root.post {
                    // 녹음 파일 이름
                    val filePath = Environment.getExternalStorageDirectory().absolutePath + "/Download/" + Date().time.toString() + ".aac"

                    // 녹음 시작
                    val audioRecorder = AudioRecorder()
                    audioRecorder.startRecording(filePath, apiManager)
                    Log.d("[mmihye] startRecording : ", filePath)
                }
            }
        }

        // 알파벳 지우기
        binding.btnBack.setOnClickListener {
            audioRecorder.stopRecording()

            if(binding.textView2.text.isNotEmpty())
            {
                val before_alpha = binding.textView2.text
                binding.textView2.text = before_alpha.substring(0, before_alpha.length - 1)

            }

        }


        // 녹음시작
        binding.btnCheck.setOnClickListener {

            // 녹음 파일 이름
            val filePath =
                Environment.getExternalStorageDirectory().absolutePath + "/Download/" + Date().time.toString() + ".aac"

            // 녹음 시작
            if (apiManager != null) {
                audioRecorder.startRecording(filePath, apiManager)
            }
            Log.d("[mmihye] startRecording : ", filePath)

        }

        binding.btnStop.setOnClickListener {
            audioRecorder.stopRecording()
        }

        binding.viewPager2.isUserInputEnabled = false


        // 페이지 바뀔때마다 callback
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                answer =quizItems[binding.viewPager2.currentItem]

                audioRecorder.stopRecording()
                binding.textView2.text = ""
                binding.viewPager2.isUserInputEnabled = false

            }
        })

        // 정답제출
        binding.btnAnswer.setOnClickListener {
//            val sharedPref = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
//            with (sharedPref!!.edit()) {
//                putString("myAnswer",binding.textView2.text.toString())
//                apply()
//            }

            audioRecorder.stopRecording()

            if(answer == binding.textView2.text.toString())
                isAnswerCorrect[binding.viewPager2.currentItem] = true
            isAnswerSubmitted[binding.viewPager2.currentItem] = true
            binding.viewPager2.isUserInputEnabled = true



            fragmentManager?.let { it1 -> QuizResultDialog(answer, isAnswerCorrect).show(it1, "resultDialog") }
//            val action = QuizFragmentDirections.actionQuizFragmentToQuizResultDialog(answer)
//            navController.navigate(action)


        }

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