package ru.netology.nmedia

fun countTransformation(count: Int): String {
    if (count >= 1000000) {
        val resultLeft = (count/1000000).toString()
        val resultRight = if (count%1000000/100000 > 0) {
            "." + (count%1000000/100000).toString()
        } else {
            ""
        }
        return "$resultLeft$resultRight" + "M"
    } else if (count >= 10000) {
        return (count/1000).toString() + "K"
    } else if (count >= 1000) {
        val resultLeft = (count/1000).toString()
        val resultRight = if (count%1000/100 > 0) {
            "." + (count%1000/100).toString()
        } else {
            ""
        }
        return "$resultLeft$resultRight" + "K"
    } else {
        return count.toString()
    }
}