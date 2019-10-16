package bluejam.datastructure.unionfind

import bluejam.datastructure.UnionFind
import bluejam.testutils.SimpleVerifier

import java.lang.StringBuilder
import java.util.*

class AojDsl1A: SimpleVerifier("aoj/DSL_1_A") {
    override fun solve(sc: Scanner): String {
        val n = sc.nextInt()
        val q = sc.nextInt()

        val uf = UnionFind(n.toLong())

        val output = StringBuilder()

        for (i in 1 .. q) {
            val c = sc.nextLong()
            val x = sc.nextLong()
            val y = sc.nextLong()

            if (c == 0L) {
                uf.unite(x, y)
            } else {
                output.appendln(if (uf.same(x, y)) "1" else "0")
            }
        }

        return output.toString()
    }
}
