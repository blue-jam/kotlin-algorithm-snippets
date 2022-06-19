package blue.bluejam.algorithms.kotlin.datastructure.segmenttree

import blue.bluejam.algorithms.kotlin.datastructure.SegmentTree
import blue.bluejam.algorithms.kotlin.testutils.SimpleVerifier
import java.lang.StringBuilder
import java.util.*
import kotlin.math.min

class AojDsl2A: SimpleVerifier("https://onlinejudge.u-aizu.ac.jp/courses/library/3/DSL/2/DSL_2_A") {
    override fun solve(sc: Scanner): String {
        val n = sc.nextInt()
        val q = sc.nextInt()
        val st = SegmentTree(n, Int.MAX_VALUE, ::min)
        val output = StringBuilder()

        for (i in 1..q) {
            val c = sc.nextInt()
            val x = sc.nextInt()
            val y = sc.nextInt()

            if (c == 0) {
                st.update(x, y)
            } else {
                output.appendln(st.query(x, y + 1).toString())
            }
        }

        return output.toString()
    }
}
