package com.voduchuy.nikeshopping.utils

import com.google.gson.JsonObject
import com.voduchuy.nikeshopping.R
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber

class ShoppingExceptionMapper{
    companion object{
        fun map(throwable: Throwable):ShoppingException{
            if (throwable is HttpException){
                try{
                    val errorJsonObject=JSONObject(throwable.response()?.errorBody()!!.toString())
                    val errorMessage=errorJsonObject.getString("message")
                    return ShoppingException(if(throwable.code()==401) ShoppingException.Type.AUTH  else ShoppingException.Type.SIMPLE, serverMessage = errorMessage)

                }catch (ex:Exception){
                    Timber.e(ex)
                }
            }
            return ShoppingException(ShoppingException.Type.SIMPLE, R.string.unknown_error)
        }
    }
}