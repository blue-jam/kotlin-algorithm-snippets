package bluejam.math.modint

import bluejam.math.ModInt
import bluejam.testutils.Verifier
import java.util.*

val MOD = 1000000007L

class AojNtl1B: Verifier<Long>("aoj/NTL_1_B") {
    override fun solve(sc: Scanner): Long {
        val m = sc.nextLong()
        val n = sc.nextLong()
        return ModInt(m, MOD).pow(n).value
    }
}
