package bluejam.math.combinatorics.combination

import bluejam.math.combinatorics.ModCombination
import bluejam.testutils.SimpleVerifier
import java.util.*

class AojDpl5E : SimpleVerifier("aoj/DPL_5_E") {
    override fun solve(sc: Scanner): String {
        val comb = ModCombination(1010, 1e9.toLong() + 7)
        val n = sc.nextLong()
        val k = sc.nextLong()

        return comb[k, n].value.toString() + "\n"
    }
}
