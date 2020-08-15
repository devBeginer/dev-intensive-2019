package ru.skillbranch.devintensive.extensions

fun String.truncate(length:Int = 16): String{
    val sb:StringBuilder = StringBuilder()
    if (this.length <= length){
        sb.append(this)
        while (sb.last() == ' '){
            sb.setLength(sb.length - 1)
        }
    }else{
        sb.append(this.substring(0, length))
        while (sb.last() == ' '){
            sb.setLength(sb.length - 1)
        }
        sb.append("...")
    }

    return sb.toString()
}