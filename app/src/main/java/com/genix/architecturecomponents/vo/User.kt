package com.genix.architecturecomponents.vo

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
        val name: String?,
        val userName: String,
        val password: String?
)