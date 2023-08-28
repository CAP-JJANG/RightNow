package com.capjjang.rightnow.ui

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.capjjang.rightnow.R
import com.capjjang.rightnow.base.BaseFragment
import com.capjjang.rightnow.databinding.FragmentStartBinding


class StartFragment : BaseFragment<FragmentStartBinding>(R.layout.fragment_start) {

    override fun initStartView() {
        super.initStartView()


        // 권한 확인
        onCheckPermission()

        binding.btnStart.setOnClickListener{
            navController.navigate(R.id.action_startFragment_to_quizFragment)
        }

    }


    override fun initDataBinding() {
        super.initDataBinding()


    }


    override fun initAfterBinding() {
        super.initAfterBinding()


    }


    private fun onCheckPermission() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.RECORD_AUDIO,
                )
            ) {
                Toast.makeText(context, "앱 실행을 위해서는 권한을 설정해야 합니다.", Toast.LENGTH_SHORT).show()
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    PERMISSIONS_REQUEST
                )
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ), PERMISSIONS_REQUEST
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_REQUEST -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "앱 실행을 위한 권한이 설정 되었습니다", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "앱 실행을 위한 권한이 취소 되었습니다", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}