fun main(args: Array<String>) {
    val start = System.currentTimeMillis();
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
    println("Memory: ${Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()} Bytes")
    println("Time: ${System.currentTimeMillis() - start}ms")

}

fun createPairs(file: String): ArrayList<CleaningGroup> {
    val pairs: ArrayList<CleaningGroup> = arrayListOf<CleaningGroup>();
    object {}.javaClass.getResourceAsStream(file)?.bufferedReader()?.readLines()?.forEach { pair ->
        val newPair = CleaningGroup(pair)
        pairs.add(newPair)
    }
    return pairs
}
