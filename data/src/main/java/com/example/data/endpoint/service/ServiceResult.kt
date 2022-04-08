package com.example.data.endpoint.service

sealed class ServiceResult<out T : Any> {
    open class Success<out T : Any>(val data: T) : ServiceResult<T>()
    open class Error(val errorModel: String) : ServiceResult<Nothing>()
}
