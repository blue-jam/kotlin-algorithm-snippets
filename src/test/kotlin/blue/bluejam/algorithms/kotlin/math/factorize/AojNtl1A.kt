package blue.bluejam.algorithms.kotlin.math.factorize

import blue.bluejam.algorithms.kotlin.math.factorize
import blue.bluejam.algorithms.kotlin.testutils.SimpleVerifier
import java.util.*

class AojNtl1A: SimpleVerifier(
    problemUrl = "https://onlinejudge.u-aizu.ac.jp/courses/library/6/NTL/1/NTL_1_A"
) {
    override fun solve(sc: Scanner): String {
        val n = sc.nextLong()
        val res = factorize(n).flatMap { p -> List(p.second.toInt()) { p.first } }
            .joinToString(" ")

        return "$n: $res\n"
    }
}
