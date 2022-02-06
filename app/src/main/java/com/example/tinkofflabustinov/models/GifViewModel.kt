package com.example.tinkofflabustinov.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tinkofflabustinov.UI.GifType
import com.example.tinkofflabustinov.data.GifPicture
import com.example.tinkofflabustinov.data.GifPicturesList
import com.example.tinkofflabustinov.network.GifApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val FAKE_URL = "tinkoff.ru"

class GifViewModel : ViewModel() {
    private val _numberOfSteps: List<MutableLiveData<Int>> = List(3) { MutableLiveData<Int>(0) }
    private val _pageNumber = mutableListOf(0, 0, 0)
    private var firstStart = true
    val numberOfSteps: List<LiveData<Int>>
        get() = _numberOfSteps

    private val _imageUrls: List<MutableLiveData<String>> = List(3) { MutableLiveData<String>("") }

    val imageUrls: List<LiveData<String>>
        get() = _imageUrls


    private val _listOfPictures: List<MutableList<GifPicture>> =
        List(3) { mutableListOf() }


    fun onRestoreConnection(type: GifType) {
        val index = _numberOfSteps[type.ordinal].value ?: 0
        if (index >= _listOfPictures[type.ordinal].size)
            queryListOfGifs(type)
        else
            setImageUrl(type)
    }

    fun onStartApp() {
        if (firstStart) {

            for (i in 0 until 3) {
                if (_numberOfSteps[i].value ?: 0 >= _listOfPictures[i].size) {
                    queryListOfGifs(
                        GifType.getByValue(i) ?: GifType.NONE
                    )
                    _imageUrls[i].value =
                        if (_listOfPictures[i].size > 0) _listOfPictures[i][0].gifURL else FAKE_URL
                }
            }
            firstStart = false
        }
    }

    private fun setImageUrl(type: GifType) {
        val index = _numberOfSteps[type.ordinal].value ?: 0
        _imageUrls[type.ordinal].value =
            if (index < _listOfPictures[type.ordinal].size)
                _listOfPictures[type.ordinal][index].gifURL else FAKE_URL
    }

    private fun queryListOfGifs(gifType: GifType) {
        GifApi.retrofitService.getListOfGifs(
            gifType.category,
            _pageNumber[gifType.ordinal].toString()
        ).enqueue(object : Callback<GifPicturesList> {
            override fun onResponse(
                call: Call<GifPicturesList>,
                response: Response<GifPicturesList>
            ) {
                _listOfPictures[gifType.ordinal].addAll(response.body()?.gifPictures ?: listOf())
                setImageUrl(gifType)
                _pageNumber[gifType.ordinal]++
            }
            override fun onFailure(call: Call<GifPicturesList>, t: Throwable) {
                setImageUrl(gifType)
            }
        })
    }

//    fun queryRandom(gifType: GifType) {
//
//        GifApi.retrofitService.getRandomGif().enqueue(object :
//            Callback<GifPicture> {
//            override fun onResponse(call: Call<GifPicture>, response: Response<GifPicture>) {
//                ("RETROFIT", "onResponse: ${response.code()}")
//                _imageUrls[gifType.ordinal].value = response.body()?.gifURL ?: FAKE_URL
//            }
//
//            override fun onFailure(call: Call<GifPicture>, t: Throwable) {
//                ("RETROFIT", "onFailure: error")
//            }
//        })
//    }

    fun increaseSteps(type: GifType) {
        _numberOfSteps[type.ordinal].value = _numberOfSteps[type.ordinal].value?.plus(1)
        val index = _numberOfSteps[type.ordinal].value ?: 0
        if (index >= _listOfPictures[type.ordinal].size)
            queryListOfGifs(type)
        else
            setImageUrl(type)


    }

    fun decreaseSteps(type: GifType) {
        _numberOfSteps[type.ordinal].value = _numberOfSteps[type.ordinal].value?.minus(1)
        setImageUrl(type)

    }

}