fun main(args: Array<String>) {
   val pairs = createPairs("input.txt");
    var numOfFullyOverlappedPairs = 0
    var numOfOverlappedPairs = 0
    pairs.forEach { pair->
        if(pair.isPairOverlapping()) {
            numOfOverlappedPairs++;
            if(pair.isPairFullyOverlapping()) {
                numOfFullyOverlappedPairs++
            }
        }

    }
    println("$numOfFullyOverlappedPairs,$numOfOverlappedPairs")
}

fun createPairs(file: String): ArrayList<Pair> {
    val inputString = object {}.javaClass.getResourceAsStream(file)?.bufferedReader()?.readLines()
    var pairs: ArrayList<Pair> = arrayListOf<Pair>();
    if (inputString != null) {
        inputString.forEach { pair ->
            val newPair = Pair(pair)
            pairs.add(newPair)
        }
    }
    return pairs
}