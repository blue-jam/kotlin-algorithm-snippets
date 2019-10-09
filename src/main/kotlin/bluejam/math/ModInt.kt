package bluejam.math

class ModInt (value: Long, val mod: Long) {
    init {
        if (mod <= 0) {
            throw IllegalArgumentException("ModInt cannot be created for non-positive mod: $mod")
        }
    }

    val value = (value % mod + mod) % mod

    private fun assertSameMod(other: ModInt) {
        if (mod != other.mod) {
            throw IllegalArgumentException(
                "You cannot operate summation, subtraction and multiplication for ModInts with different mods: $mod, ${other.mod}"
            )
        }
    }

    operator fun unaryMinus(): ModInt {
        return ModInt(mod - value, mod)
    }

    operator fun plus(other: ModInt): ModInt {
        assertSameMod(other)

        return ModInt((value + other.value) % mod, mod)
    }

    operator fun plus(other: Long): ModInt {
        return plus(ModInt(other, mod))
    }

    operator fun minus(other: ModInt): ModInt {
        assertSameMod(other)

        return plus(-other)
    }

    operator fun minus(other: Long): ModInt {
        return minus(ModInt(-other, mod))
    }

    operator fun times(other: ModInt): ModInt {
        assertSameMod(other)

        return ModInt((value * other.value) % mod, mod)
    }

    operator fun times(other: Long): ModInt {
        return times(ModInt(other, mod))
    }

    fun pow(k: Long): ModInt {
        var res = 1L
        var a = value
        var l = k

        while (l > 0) {
            if ((l and 1L) == 1L) {
                res = (res * a) % mod
            }

            a = a * a % mod
            l /= 2L
        }

        return ModInt(res, mod)
    }

    fun inv(): ModInt {
        return pow(mod - 2)
    }

    operator fun div(other: ModInt): ModInt {
        assertSameMod(other)
        if (other.value == 0L) {
            throw ArithmeticException()
        }

        return times(other.inv())
    }

    operator fun div(other: Long): ModInt {
        return div(ModInt(other, mod))
    }
}
