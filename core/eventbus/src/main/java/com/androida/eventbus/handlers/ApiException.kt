package com.androida.handlers

data class ApiException(override val message: String, val code: Int) : Exception(message)
