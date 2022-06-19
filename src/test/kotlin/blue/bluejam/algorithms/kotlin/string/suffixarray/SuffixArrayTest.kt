package blue.bluejam.algorithms.kotlin.string.suffixarray

import blue.bluejam.algorithms.kotlin.string.SuffixArray
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SuffixArrayTest {
    @Test
    fun `SA-IS`() {
        val sa = SuffixArray("abracacabra")

        assertTrue(sa.sa.contentEquals(intArrayOf(11, 10, 7, 0, 5, 3, 8, 1, 6, 4, 9, 2)))
        assertTrue(sa.rank.contentEquals(intArrayOf(3, 7, 11, 5, 9, 4, 8, 2, 6, 10, 1, 0)))
        assertTrue(sa.lcp.contentEquals(intArrayOf(0, 1, 4, 1, 3, 0, 3, 0, 2, 0, 2, 0)))
    }
}
