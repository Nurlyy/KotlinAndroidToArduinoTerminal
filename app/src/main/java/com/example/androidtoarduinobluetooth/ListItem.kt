package com.example.androidtoarduinobluetooth

import java.io.Serializable

data class ListItem(
    var name:String,
    var mac:String
): Serializable
