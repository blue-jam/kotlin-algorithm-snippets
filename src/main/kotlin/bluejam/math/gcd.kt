package bluejam.math

fun gcd(x: Long, y: Long): Long = if (y == 0L) x else gcd(y, x % y)

fun lcm(x: Long, y: Long): Long = x / gcd(x, y) * y
