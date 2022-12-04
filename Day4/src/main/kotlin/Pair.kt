class Pair internal constructor(group: String) {
    private var firstSection: ArrayList<Int>? = arrayListOf<Int>()
    private var secondSection: ArrayList<Int>? = arrayListOf<Int>()

    init {
        this.getSectionFromString(group)
    }

    private fun getSectionFromString(input: String){
        val pair = input.split(",")
        var index = 0
        pair.forEach { section ->
            val start = section.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].toInt()
            val end = section.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1].toInt()
            if (index.equals(0)) {
                this.firstSection = arrayListOf<Int>(start,end)
            } else {
                this.secondSection = arrayListOf<Int>(start,end)
            }
            index += 1
        }
    }

     fun isPairFullyOverlapping(): Boolean {
        if(this.secondSection?.get(0)!! >= this.firstSection?.get(0)!! && this.secondSection?.get(1)!! <= this.firstSection?.get(1)!!) {
            return true
        }
        if(this.firstSection?.get(0)!! >= this.secondSection?.get(0)!! && this.firstSection?.get(1)!! <= this.secondSection?.get(1)!!) {
            return true
        }
        return false
    }
    fun isPairOverlapping(): Boolean {
        if(this.secondSection?.get(0)!! >= this.firstSection?.get(0)!!) {
            if(this.secondSection?.get(0)!! in this.firstSection?.get(0)!!..this.firstSection?.get(1)!!) {
                return true
            }
        }
        if(this.firstSection?.get(0)!! >= this.secondSection?.get(0)!!) {
            if(this.firstSection?.get(0)!! in this.secondSection?.get(0)!!..this.secondSection?.get(1)!!) {
                return true
            }
        }
        return false
    }

}