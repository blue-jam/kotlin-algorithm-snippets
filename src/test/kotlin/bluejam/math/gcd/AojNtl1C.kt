package bluejam.math.gcd

import bluejam.math.lcm

fun solveAojNtl1C(a: List<Long>): Long {
    return a.fold(1L) { x, y -> lcm(x, y)}
}

fun main(args: Array<String>) {
    val N = readLine()!!.toInt()
    val a = readLine()!!.split(" ").map { it.toLong() }

    println(solveAojNtl1C(a))
}
