enum class Analytes {
    C,N,P,K,S,H2O
}

enum class Inputs {
    FLSC, GLSC, LCE, BAIADA, PROTEN
}

class Cube ( val source: Inputs) {

    var remainingVolume: Double = 1000.0
    val analysis: MutableMap<Analytes, Double?> = mutableMapOf()
    var wobbleType: WobbleBehaviour = TenPercentWobble()

    init {
        Analytes.values().forEach {
            analysis.put(it, null)
        }
    }

    fun setAnalysis( inputdata: MutableMap<Analytes, Double?>) {
        remainingVolume = 1000.0
        updateAnalysis(wobbleType.wobble(inputdata))
        updateReamaining()
    }

    private fun updateAnalysis(inputdata: MutableMap<Analytes, Double?>) {
        inputdata.forEach {
            analysis.put(it.key, it.value)
        }
    }

    private fun updateReamaining() {
        var kilosUsed: Double = 0.0
        for ( value in analysis.values) {
            value?.let { kilosUsed += value}
        }
        remainingVolume -= kilosUsed
    }

}

object Pile {

    val thePile:MutableList<Cube> = mutableListOf()

    val pileSize: Int
        get() = thePile.size

    fun getAnalysis(): MutableMap<Analytes, Double?> {
        val aveAnalysis: MutableMap<Analytes, Double?> = mutableMapOf()
        enumValues<Analytes>().forEach { a->
            var runningTotal: Double = 0.0
            for ( cube in thePile ) {
                val thisAnalsys = cube.analysis[a]
                if (thisAnalsys != null) runningTotal += thisAnalsys
            }
            aveAnalysis.put(a,runningTotal/thePile.size)
        }
        return aveAnalysis
    }
}