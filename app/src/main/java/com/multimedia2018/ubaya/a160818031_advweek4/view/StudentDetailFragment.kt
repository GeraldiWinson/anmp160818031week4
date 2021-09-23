package com.multimedia2018.ubaya.a160818031_advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.multimedia2018.ubaya.a160818031_advweek4.R
import com.multimedia2018.ubaya.a160818031_advweek4.util.loadImage
import com.multimedia2018.ubaya.a160818031_advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            var studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentId)
            observeDetailViewModel()
        }
    }

    fun observeDetailViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtId.setText(viewModel.studentLD.value?.id)
            txtName.setText(viewModel.studentLD.value?.name)
            txtBod.setText(viewModel.studentLD.value?.bod)
            txtPhone.setText(viewModel.studentLD.value?.phone)
            imageView2.loadImage(viewModel.studentLD.value?.photoUrl.toString(), progressBarDetail)

            var student = it
            btnNotif.setOnClickListener{
                Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                    }
            }
        })
    }
}