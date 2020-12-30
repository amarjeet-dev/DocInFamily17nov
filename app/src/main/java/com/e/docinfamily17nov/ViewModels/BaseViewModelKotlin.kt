package com.e.docinfamily17nov.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModelKotlin : ViewModel() {

    private var loading: MutableLiveData<Boolean>? = null
    private var errorCode: MutableLiveData<Int>? = null
    private var message: MutableLiveData<String>? = null
    private var isUnAuthorized: MutableLiveData<Boolean>? = null
    private var isdataFound: MutableLiveData<Boolean>? = null

    private val compositeDisposable = CompositeDisposable()


    protected fun addDisposable(disposable: Disposable?) {
        compositeDisposable.add(disposable!!)
    }

    fun getLoading(): MutableLiveData<Boolean>? {
        if (loading == null) loading = MutableLiveData()
        return loading
    }

    fun getErrorCode(): MutableLiveData<Int>? {
        if (errorCode == null) errorCode = MutableLiveData()
        return errorCode
    }

    fun getMessage(): MutableLiveData<String>? {
        if (message == null) message = MutableLiveData()
        return message
    }

    fun getIsUnAuthorized(): MutableLiveData<Boolean>? {
        if (isUnAuthorized == null) isUnAuthorized = MutableLiveData()
        return isUnAuthorized
    }


    fun getIsDataFound(): MutableLiveData<Boolean>? {
        if (isdataFound == null) isdataFound = MutableLiveData()
        return isdataFound
    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}