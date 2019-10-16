package bluejam.math.gcd

import bluejam.math.extendedEuclideanAlgorithm
import bluejam.testutils.Verifier
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class AojNtl1E: Verifier<Pair<Long, Long>>("aoj/NTL_1_E") {
    override fun assertResult(expected: Pair<Long, Long>, actual: Pair<Long, Long>) {
        assertEquals(expected, actual)
    }

    override fun readAnswerFile(sc: Scanner): Pair<Long, Long> = Pair(sc.nextLong(), sc.nextLong())

    override fun solve(sc: Scanner): Pair<Long, Long> {
        val a = sc.nextLong()
        val b = sc.nextLong()

        val (x, y) = extendedEuclideanAlgorithm(a, b)

        return Pair(x, y)
    }
}
