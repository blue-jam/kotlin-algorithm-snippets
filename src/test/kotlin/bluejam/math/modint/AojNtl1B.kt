package bluejam.math.modint

import bluejam.math.ModInt
import bluejam.testutils.Verifier
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class AojNtl1B: Verifier<Long>(
    problemUrl = "https://onlinejudge.u-aizu.ac.jp/courses/library/6/NTL/1/NTL_1_B"
) {
    val MOD = 1000000007L

    override fun readAnswerFile(sc: Scanner): Long = sc.nextLong()

    override fun solve(sc: Scanner): Long {
        val m = sc.nextLong()
        val n = sc.nextLong()
        return ModInt(m, MOD).pow(n).value
    }

    override fun assertResult(expected: Long, actual: Long) {
        assertEquals(expected, actual)
    }
}
