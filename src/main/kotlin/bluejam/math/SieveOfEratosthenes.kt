package bluejam.math

import java.util.*

class SieveOfEratosthenes(n: Long) {
    private val isPrime = Array(n.toInt() + 1) { true }

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

    fun get(i: Long): Boolean {
        return isPrime[i.toInt()]
    }
}
