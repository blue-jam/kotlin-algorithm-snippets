package bluejam.string.suffixarray

import bluejam.string.SuffixArray
import bluejam.testutils.SimpleVerifier
import java.util.*

class LcSuffixArray: SimpleVerifier("https://judge.yosupo.jp/problem/suffixarray", 6) {
    override fun solve(sc: Scanner): String {
        val s = sc.next()
        val sa = SuffixArray(s)

        return sa.sa.drop(1).joinToString(" ") + "\n"
    }
}
