fun main() {

    for (i in 1..1000) {
        val testCube = Cube(Inputs.FLSC)
        val data: List<Double> = listOf(144.4, 8.5, 4.4, 4.0, 4.1, 200.0)
        val analysis = (Analytes.values() zip data)
            .toMap() as MutableMap<Analytes, Double?>
        testCube.setAnalysis(analysis)
        Pile.thePile.add(testCube)
    }

    println(Pile.pileSize)
    println(Pile.getAnalysis())

}

