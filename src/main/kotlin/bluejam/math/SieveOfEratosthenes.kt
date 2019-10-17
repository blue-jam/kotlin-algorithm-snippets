package bluejam.math

import java.util.*

/**
 * Generate a list of primes less than or equal to `n`.
 */
class SieveOfEratosthenes(n: Long) {
    private val isPrime = Array(n.toInt() + 1) { true }

    /**
     * A list of primes less than or equal to `n`.
     */
    val primes: List<Long>

    init {
        isPrime[0] = false
        isPrime[1] = false
        val primeList = ArrayList<Long>()

        for (i in 2..n.toInt()) {
            if (!isPrime[i]) {
                continue
            }

            primeList.add(i.toLong())
            for (j in i * 2 .. n.toInt() step i) {
                isPrime[j] = false
            }
        }

        primes = Collections.unmodifiableList(primeList)
    }

    /**
     * @return `true` if `i` is prime.
     */
    fun get(i: Long): Boolean {
        return isPrime[i.toInt()]
    }
}
