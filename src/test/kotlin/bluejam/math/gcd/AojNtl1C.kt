package bluejam.math.gcd

import bluejam.math.lcm
import bluejam.testutils.Verifier
import java.util.*

class AojNtl1C: Verifier<Long>("aoj/NTL_1_C") {
    override fun solve(sc: Scanner): Long {
        val N = sc.nextInt()
        val a = Array(N) { sc.nextLong() }

        return a.fold(1L, ::lcm)
    }
}
