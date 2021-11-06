package com.multimedia2018.ubaya.a160818031_advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.multimedia2018.ubaya.a160818031_advweek4.R
import com.multimedia2018.ubaya.a160818031_advweek4.databinding.FragmentStudentDetailBinding
import com.multimedia2018.ubaya.a160818031_advweek4.model.Student
import com.multimedia2018.ubaya.a160818031_advweek4.util.loadImage
import com.multimedia2018.ubaya.a160818031_advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), SDButtonNotificationClickListener, SDButtonUpdateClickListener {
    private lateinit var viewModel:DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //Week 4 old
        //return inflater.inflate(R.layout.fragment_student_detail, container, false)

        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
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
            dataBinding.student = it
            dataBinding.notifListener = this
            dataBinding.updateListener = this

        /* Week 4 old
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
                        MainActivity.showNotification(student.name.toString(),
                            "A new notification created",
                            R.drawable.ic_baseline_person_24)
                    }
            }
            */
        })
    }

    override fun onSDButtonUpdateClick(v: View) {
        //Transisi ke halaman sebelumnya
    }

    override fun onSDButtonNotificationClick(v: View, obj: Student) {
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                MainActivity.showNotification(dataBinding.student?.name.toString(),
                    "A new notification created",
                    R.drawable.ic_baseline_person_24)
            }
    }
}