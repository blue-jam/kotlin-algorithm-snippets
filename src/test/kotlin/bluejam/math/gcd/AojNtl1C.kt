package bluejam.math.gcd

import bluejam.math.lcm
import bluejam.testutils.Verifier
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class AojNtl1C: Verifier<Long>(
    problemUrl = "https://onlinejudge.u-aizu.ac.jp/courses/library/6/NTL/1/NTL_1_C"
) {
    override fun readAnswerFile(sc: Scanner): Long = sc.nextLong()

    override fun solve(sc: Scanner): Long {
        val N = sc.nextInt()
        val a = Array(N) { sc.nextLong() }

        return a.fold(1L, ::lcm)
    }

    override fun assertResult(expected: Long, actual: Long) {
        assertEquals(expected, actual)
    }
}
