package ru.byprogminer.modbot

interface Chat {

    val id: Long
    val agent: Agent

    val title: String
    val ownerId: Long
}
