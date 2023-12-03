fun List<List<*>>.printMultiLineNoSpaces() {
    for (i in this) {
        for (j in i) {
            print(j)
        }
        print("\n")
    }
}

fun List<List<*>>.printMultiLine() {
    for (i in this) {
        println(i)
    }
}

fun <T> List<T>.containsAny(other: List<T>): Boolean {
    for (i in other) {
        if (this.contains(i)) return true
    }

    return false
}

/**
 * sets are annoying to work with
 */
fun <T> List<T>.unique(): List<T> {
    val finalList = mutableListOf<T>()

    for (i in this) {
        if (!finalList.contains(i)) finalList += i
    }

    return finalList
}


fun String.isInt(): Boolean = this.toIntOrNull() != null