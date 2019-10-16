package bluejam.math.sieve

import bluejam.math.SieveOfEratosthenes
import bluejam.testutils.Verifier
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*

class Aoj1232: Verifier<List<Pair<Long, Long>>>("aoj/1232") {
    override fun readAnswerFile(sc: Scanner): List<Pair<Long, Long>> {
        val list = ArrayList<Pair<Long, Long>>()

        while (sc.hasNextLong()) {
            list.add(Pair(sc.nextLong(), sc.nextLong()))
        }

        return list
    }

    override fun solve(sc: Scanner): List<Pair<Long, Long>> {
        val sieve = SieveOfEratosthenes(200000)
        val primes = sieve.primes
        val answers = ArrayList<Pair<Long, Long>>()

        while (true) {
            val m = sc.nextLong()
            val a = sc.nextLong()
            val b = sc.nextLong()
            var res = Pair(2L, 2L)

            if (m == 0L) {
                return answers
            }

            for (p in primes) {
                if (p * p > m) {
                    break
                }

                for (q in primes) {
                    if (p * q > m) {
                        break
                    }

                    if (a * q <= b * p && res.first * res.second < p * q) {
                        res = Pair(p, q)
                    }
                }
            }

            answers.add(res)
        }
    }

    override fun assertResult(expected: List<Pair<Long, Long>>, actual: List<Pair<Long, Long>>) {
        assertEquals(expected, actual)
    }
}
