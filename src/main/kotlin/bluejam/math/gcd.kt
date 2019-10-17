package bluejam.math

/**
 * Calculate the greatest common divisor of `x` and `y` with Euclidean algorithm
 */
tailrec fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

/**
 * Calculate the least common multiple of `x` and `y`
 */
fun lcm(x: Long, y: Long): Long = x / gcd(x, y) * y

/**
 * Calculate `a` and `b` such that `a x + b y = [x, y]` where `[x, y]` is GCD of x and y.
 * @return A triple of `(a, b, [x,y])` i.e. `t.first * x + t.second * y = t.third`
 */
fun extendedEuclideanAlgorithm(x: Long, y: Long): Triple<Long, Long, Long> {
    return if (y == 0L) {
        Triple(1, 0, x)
    } else {
        val t = extendedEuclideanAlgorithm(y, x % y)
        Triple(t.second, t.first - t.second * (x / y), t.third)
    }
}
