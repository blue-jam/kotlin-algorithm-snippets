package bluejam.math.modint

import bluejam.math.ModInt

val MOD = 1000000007L

fun solve(m: Long, n: Long): Long {
    return ModInt(m, MOD).pow(n).value
}

fun main(args: Array<String>) {
    val (m, n) = readLine()!!.split(" ").map { it.toLong() }

    println(solve(m, n))
}
