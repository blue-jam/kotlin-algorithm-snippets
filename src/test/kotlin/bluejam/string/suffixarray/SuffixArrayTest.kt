package bluejam.string.suffixarray

import bluejam.string.SuffixArray
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SuffixArrayTest {
    @Test
    fun `SA-IS`() {
        val sa = SuffixArray("abracacabra")
        assertTrue(intArrayOf(11, 10, 7, 0, 5, 3, 8, 1, 6, 4, 9, 2).contentEquals(sa.sa))
    }
}
