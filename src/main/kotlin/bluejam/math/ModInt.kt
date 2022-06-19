package bluejam.math

/**
 * An integer under a (prime) modulus.
 *
 * For any operations with two arguments, they should have a same modulus.
 * If they don't, throws [IllegalArgumentException].
 *
 * First create a factory by `val factory = ModInt.Factory(mod)`.
 * Then, `factory.valueOf(value)` to create ModInt instance.
 */
class ModInt private constructor(value: Long, val mod: Long) {
    val value: Long

    init {
        if (mod <= 0) {
            throw IllegalArgumentException("ModInt cannot be created for non-positive modulus: $mod")
        }
        this.value = (value % mod + mod) % mod
    }

    /**
     * Factory to create `ModInt` instance.
     *
     * @param mod a modulus. Should be a positive integer. If it's not prime, division won't work.
     * @constructor Create a factory with the modulus `mod`.
     * If the `mod` isn't positive, throws an [IllegalArgumentException].
     */
    class Factory(val mod: Long) {
        val zero = valueOf(0L)
        val one = valueOf(1L)
        val two = valueOf(2L)

        fun valueOf(value: Long): ModInt {
            return ModInt(value, mod)
        }
    }

    private fun assertSameMod(other: ModInt) {
        if (mod != other.mod) {
            throw IllegalArgumentException(
                "You cannot operate summation, subtraction and multiplication for ModInts with different moduli: $mod, ${other.mod}"
            )
        }
    }

    operator fun unaryMinus() = ModInt(mod - value, mod)

    operator fun plus(other: ModInt): ModInt {
        assertSameMod(other)

        return ModInt((value + other.value) % mod, mod)
    }

    operator fun plus(other: Long) = plus(ModInt(other, mod))

    operator fun minus(other: ModInt): ModInt {
        assertSameMod(other)

        return plus(-other)
    }

    operator fun minus(other: Long) = minus(ModInt(other, mod))

    operator fun times(other: ModInt): ModInt {
        assertSameMod(other)

        return ModInt((value * other.value) % mod, mod)
    }

    operator fun times(other: Long) = times(ModInt(other, mod))

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

    /**
     * Returns an inverse element of the value.
     * To ensure `m * m.inv() = 1`, the modulus should be prime, since it uses Fermat's little theorem.
     */
    fun inv() = pow(mod - 2)

    operator fun div(other: ModInt): ModInt {
        assertSameMod(other)
        if (other.value == 0L) {
            throw ArithmeticException()
        }

        return times(other.inv())
    }

    operator fun div(other: Long) = div(ModInt(other, mod))
}
