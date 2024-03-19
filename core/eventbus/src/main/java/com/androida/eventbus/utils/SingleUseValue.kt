package com.androida.eventbus.utils

import androidx.compose.runtime.Composable

class SingleUseValue<T>(_value: T?) {

    private var used = false

    var value = _value
        set(value) {
            used = false
            field = value
        }

    @Composable
    fun peekValue(block: @Composable (event: T) -> Unit) {
        if (!used && value != null) {
            used = true
            block(value!!)
        }
    }

    fun peekValueOr(defaultValue: T): T {
        if (!used && value != null) {
            used = true
            return value!!
        }
        return defaultValue
    }

    override fun equals(other: Any?): Boolean {
        val otherSingleUseValue = other as? SingleUseValue<*>
        return this.value == otherSingleUseValue?.value && this.used == otherSingleUseValue?.used
    }

    override fun hashCode(): Int {
        var result = used.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }
}

fun <T> T.toSingleUseValue(): SingleUseValue<T> = SingleUseValue(this)