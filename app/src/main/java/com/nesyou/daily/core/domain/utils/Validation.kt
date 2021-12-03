package com.nesyou.daily.core.domain.utils


class Validation(private val a: String, private val b: String) {
    private val errors = mutableListOf<String>()
    fun required() = apply {
        if (a.isBlank()) {
            errors.add("$b can't be empty")
        }
    }

    fun min(min: Int) = apply {
        if (a.length < min) {
            errors.add("$b must be greater than $min characters")
        }
    }

    fun isEmail() = apply {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(a).matches()) {
            errors.add("Enter a valid email address")
        }
    }

    fun build(): String? = errors.firstOrNull()
}

fun String.validate(name: String): Validation =
    Validation(this, name)

