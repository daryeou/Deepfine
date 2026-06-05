package com.wonjo.deepfine.core.data.auth

import java.security.MessageDigest
import javax.inject.Inject

internal class PasswordHasher @Inject constructor() {
    fun hash(
        email: String,
        password: String,
    ): String {
        val source = "$email:$password"
        val bytes = MessageDigest.getInstance("SHA-256")
            .digest(source.toByteArray())

        return bytes.joinToString(separator = "") { "%02x".format(it.toInt() and 0xff) }
    }
}
