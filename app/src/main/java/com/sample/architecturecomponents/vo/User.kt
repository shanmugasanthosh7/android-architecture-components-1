package com.sample.architecturecomponents.vo

import androidx.room.Entity
import androidx.room.Index
import java.util.*

@Entity(
        indices = [
            Index("id"),
            Index("userName")],
        primaryKeys = ["id", "userName"])
data class User(
        val id: String = UUID.randomUUID().toString(),
        var name: String?,
        var userName: String,
        var password: String?
)