interface WobbleBehaviour {
    fun wobble(inputAnalysis: MutableMap<Analytes, Double?>) : MutableMap<Analytes, Double?>
}

class TenPercentWobble: WobbleBehaviour {

    override fun wobble( inputAnalysis: MutableMap<Analytes, Double?>): MutableMap<Analytes, Double?> {

        val dataToReturn = mutableMapOf<Analytes, Double?>()

        for ((key, value) in inputAnalysis) {
            value?.let {
                val min = value * 0.9
                val max = value * 1.1
                val choices = listOf<Double>(min, max)
                val output = choices.random()
                dataToReturn.put(key, output)
            }
        }

        return dataToReturn
    }

}