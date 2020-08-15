package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder

object Utils {
    fun parseFullName(fullName : String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.split(" ")
        val firstName = if(parts?.getOrNull(0) != "") parts?.getOrNull(0) else null
        val lastName = if(parts?.getOrNull(1) != "") parts?.getOrNull(1) else null
        return Pair(firstName, lastName)
        //return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val dictionary: Map<Char, String> = mapOf(
            'а' to "a",
            'А' to "A",
            'б' to  "b",
            'Б' to  "B",
            'в' to "v",
            'В' to "V",
            'г' to "g",
            'Г' to "G",
            'д' to "d",
            'Д' to "D",
            'е' to "e",
            'Е' to "E",
            'ё' to "e",
            'Ё' to "E",
            'ж' to "zh",
            'Ж' to "Zh",
            'з' to "z",
            'З' to "Z",
            'и' to "i",
            'И' to "I",
            'й' to "i",
            'Й' to "I",
            'к' to "k",
            'К' to "K",
            'л' to "l",
            'Л' to "L",
            'м' to "m",
            'М' to "M",
            'н' to "n",
            'Н' to "N",
            'о' to "o",
            'О' to "O",
            'п' to "p",
            'П' to "P",
            'р' to "r",
            'Р' to "R",
            'с' to "s",
            'С' to "S",
            'т' to "t",
            'Т' to "T",
            'у' to "u",
            'У' to "U",
            'ф' to "f",
            'Ф' to "F",
            'х' to "h",
            'Х' to "H",
            'ц' to "c",
            'Ц' to "C",
            'ч' to "ch",
            'Ч' to "Ch",
            'ш' to "sh",
            'Ш' to "Sh",
            'щ' to "sh",
            'Щ' to "Sh",
            'ъ' to "",
            'ы' to "i",
            'Ы' to "I",
            'ь' to "",
            'э' to "e",
            'Э' to "E",
            'ю' to "yu",
            'Ю' to "Yu",
            'я' to "ya",
            'Я' to "Ya",
            ' ' to divider)
        var strBuilder: StringBuilder = StringBuilder()

        for (char in payload.toCharArray()){
            when(char){
                in dictionary.keys -> strBuilder.append(dictionary[char])
                else -> strBuilder.append(char)
            }
        }
        return strBuilder.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstChar:Char? = firstName?.getOrNull(0)?.toUpperCase()
        if (firstChar == ' ') firstChar = null

        var secondChar:Char? = lastName?.getOrNull(0)?.toUpperCase()
        if (secondChar == ' ') secondChar = null

        val initial:String? = if (firstChar != null){
            if (secondChar != null){
                "$firstChar$secondChar"
            }else{
                "$firstChar"
            }
        }else if(secondChar != null){
            "$secondChar"
        }else{
            null
        }
        return initial
    }
}