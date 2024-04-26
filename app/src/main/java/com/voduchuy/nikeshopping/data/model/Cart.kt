package com.voduchuy.nikeshopping.data.model

import java.util.Date

data class Cart(val id:Int,val userId:Int,val date:Date,val products:List<ProductCart>)