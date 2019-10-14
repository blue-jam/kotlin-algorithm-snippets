package bluejam.math.modint

import bluejam.math.ModInt
import bluejam.testutils.Verifier
import java.util.*

class AojNtl1B: Verifier<Long>("aoj/NTL_1_B") {
    val MOD = 1000000007L

    override fun readAnswerFile(sc: Scanner): Long = sc.nextLong()

    override fun solve(sc: Scanner): Long {
        val m = sc.nextLong()
        val n = sc.nextLong()
        return ModInt(m, MOD).pow(n).value
    }
}
