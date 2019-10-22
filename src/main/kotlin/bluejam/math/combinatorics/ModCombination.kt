package bluejam.math.combinatorics

import bluejam.math.ModInt

class ModCombination(val n: Long, val mod: Long) {
    private val fact = Array(n.toInt() + 1) { ModInt(1, mod) }
    private val invfact = Array(n.toInt() + 1) { ModInt(1, mod) }

    init {
        for (i in 2..n.toInt()) {
            fact[i] = fact[i - 1] * i.toLong()
            invfact[i] = fact[i].inv()
        }
    }

    operator fun get(n: Long, m: Long): ModInt {
        if (m > n) {
            return ModInt(0, mod)
        }

        return fact[n.toInt()] * invfact[m.toInt()] * invfact[(n - m).toInt()]
    }
}
