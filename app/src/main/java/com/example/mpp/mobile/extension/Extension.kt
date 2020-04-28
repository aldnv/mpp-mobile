package com.example.mpp.mobile.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.commit(block: FragmentTransaction.() -> Unit){
    val transaction = beginTransaction()
    block(transaction)
    transaction.commit()
}

fun FragmentActivity.commitTransaction(block: FragmentTransaction.() -> Unit){
    supportFragmentManager.commit(block)
}

fun Fragment.commitActivityTransaction(block: FragmentTransaction.() -> Unit){
    requireActivity().commitTransaction(block)
}