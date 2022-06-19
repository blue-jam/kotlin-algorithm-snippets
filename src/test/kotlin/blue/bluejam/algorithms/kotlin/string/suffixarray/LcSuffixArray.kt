package blue.bluejam.algorithms.kotlin.string.suffixarray

import blue.bluejam.algorithms.kotlin.string.SuffixArray
import blue.bluejam.algorithms.kotlin.testutils.SimpleVerifier
import java.util.*

class LcSuffixArray: SimpleVerifier("https://judge.yosupo.jp/problem/suffixarray") {
    override fun solve(sc: Scanner): String {
        val s = sc.next()
        val sa = SuffixArray(s)

        return sa.sa.drop(1).joinToString(" ") + "\n"
    }
}
