package com.example.androidtoarduinobluetooth

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.*

@SuppressLint("MissingPermission")
class ConnectThread(private val device: BluetoothDevice): Thread() {
    val uuid = "00001101-0000-1000-8000-00805F9B34FB"
    var mySocket : BluetoothSocket? = null
    lateinit var rThread: ReceiveThread

    init {
        try {
            mySocket = device.createRfcommSocketToServiceRecord(UUID.fromString(uuid))
        }catch (i: IOException){

        }
    }

    override fun run() {
        try{
            Log.d("MyLog","Connecting...")
            mySocket?.connect()
            Log.d("MyLog","Connected")
            rThread = ReceiveThread(mySocket!!)
            rThread.start()
        }catch(i: IOException){
            closeConnection()
        }
    }

    fun closeConnection(){
        try{
            mySocket?.close()
        }catch(i: IOException){

        }
    }

}