package blue.bluejam.algorithms.kotlin.math.combinatorics

import blue.bluejam.algorithms.kotlin.math.ModInt

class ModCombination(val n: Long, private val factory: ModInt.Factory) {
    private val fact = Array(n.toInt() + 1) { factory.valueOf(1) }
    private val invfact = Array(n.toInt() + 1) { factory.valueOf(1) }

    init {
        for (i in 2..n.toInt()) {
            fact[i] = fact[i - 1] * i.toLong()
            invfact[i] = fact[i].inv()
        }
    }

    constructor(n: Long, mod: Long) : this(n, ModInt.Factory(mod)) {
    }

    operator fun get(n: Long, m: Long): ModInt {
        if (m > n) {
            return factory.valueOf(0)
        }

        return fact[n.toInt()] * invfact[m.toInt()] * invfact[(n - m).toInt()]
    }
}
