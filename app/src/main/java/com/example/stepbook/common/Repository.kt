package com.example.stepbook.common

interface Repository<T> {

    fun refresh() : Boolean
    fun size() : Int
    fun get(position:Int) : T
    fun getAll() : Array<T>
}