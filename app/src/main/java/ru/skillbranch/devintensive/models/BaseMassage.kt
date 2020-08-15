package ru.skillbranch.devintensive.models

import java.util.*

abstract class BaseMassage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMassage():String
    companion object AbstractFactory{
        var lastId = -1
        fun makeMassage(from: User?, chat: Chat, date: Date = Date(), type:String="text", payload:Any?, isIncoming: Boolean = false):BaseMassage{
            lastId++
            return when(type){
                "image"->ImageMassage("$lastId", from, chat, date = date, image = payload as String, isIncoming = isIncoming)
                else->TextMassage("$lastId", from, chat, date = date, text = payload as String, isIncoming = isIncoming)
            }
        }
    }
}