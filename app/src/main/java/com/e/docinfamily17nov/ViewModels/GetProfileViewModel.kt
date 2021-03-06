package com.e.docinfamily17nov.ViewModels

import androidx.lifecycle.MutableLiveData
import com.e.docinfamily17nov.Model.GetProfileExample
import com.e.docinfamily17nov.Model.GetProfileUserData

import com.e.docinfamily17nov.WebServices.APICallMethodsKotlin
import com.e.docinfamily17nov.WebServices.APIHandlerKotlin
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.lang.Exception

class GetProfileViewModel: BaseViewModelKotlin() {

    var profileData : GetProfileUserData?=null

    private var observerResponse: MutableLiveData<GetProfileExample>? = null
 fun observerServerResponse(): MutableLiveData<GetProfileExample> {
        if (observerResponse == null) observerResponse = MutableLiveData()
        return observerResponse as MutableLiveData<GetProfileExample>
    }

    fun init(token: String)
    {
        postDatatoServer(token)
    }

    private fun postDatatoServer( token: String)
    {
        val handler: APICallMethodsKotlin = APIHandlerKotlin.getHandler()!!

        addDisposable(
            handler.GetProfile("Bearer "+token,"application/json","application/json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GetProfileExample?>()
                {
                    override fun onSuccess(successResponse: GetProfileExample) {
                        getLoading()!!.postValue(false)
                        if (successResponse != null) {
                            getIsDataFound()!!.postValue(true)
                            observerServerResponse().postValue(successResponse)

                        } else {
                            getIsDataFound()!!.postValue(false)
                            getMessage()!!.postValue("No Data Found.")
                        }
                    }

                    override fun onError(e: Throwable) {
                        try {
                            val error: HttpException = e as HttpException
                            val errorBody: String? = error.response().errorBody()!!.string()

                            //logic on token expire
                            val errorCode: Int? = error.code()
                            val errorMessage: String? = error.message()
                            if (errorCode == 401 ) {
                                getMessage()!!.postValue(errorMessage + "")
                            } else {
                                getMessage()!!.postValue(errorBody + "")
                            }

                            getIsDataFound()!!.postValue(false)
                            getLoading()!!.postValue(false)
                        } catch (ex: Exception) {
                            ex.printStackTrace()
                            getIsDataFound()!!.postValue(false)
                            getLoading()!!.postValue(false)
                        }
                    }

                })
        )
    }
}