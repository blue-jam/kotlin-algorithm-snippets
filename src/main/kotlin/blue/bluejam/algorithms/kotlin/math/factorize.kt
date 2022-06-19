package blue.bluejam.algorithms.kotlin.math

import java.util.*

fun factorize(n: Long): List<Pair<Long, Long>> {
    val list = ArrayList<Pair<Long, Long>>()
    var m = n
    var k = 2L

    while (m > 1 && k * k <= m) {
        var s = 0L
        while (m % k == 0L) {
            m /= k
            s++
        }
        if (s > 0) {
            list.add(Pair(k, s))
        }
        k++
    }

    if (m > 1) {
        list.add(Pair(m, 1L))
    }

    return list
}
