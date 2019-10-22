package bluejam.math.factorize

import bluejam.math.factorize
import bluejam.testutils.SimpleVerifier
import java.util.*

class AojNtl1A: SimpleVerifier("aoj/NTL_1_A") {
    override fun solve(sc: Scanner): String {
        val n = sc.nextLong()
        val res = factorize(n).flatMap { p -> List(p.second.toInt()) { p.first } }
            .joinToString(" ")

        return "$n: $res\n"
    }
}
