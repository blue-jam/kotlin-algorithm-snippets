package bluejam.math

tailrec fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

fun lcm(x: Long, y: Long): Long = x / gcd(x, y) * y

fun extendedEuclideanAlgorithm(x: Long, y: Long): Triple<Long, Long, Long> {
    return if (y == 0L) {
        Triple( 1, 0, x)
    } else {
        val t = extendedEuclideanAlgorithm(y, x % y)
        Triple(t.second, t.first - t.second * (x / y), t.third)
    }
}
