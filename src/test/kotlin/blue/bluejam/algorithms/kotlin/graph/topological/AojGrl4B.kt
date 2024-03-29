package blue.bluejam.algorithms.kotlin.graph.topological

import blue.bluejam.algorithms.kotlin.graph.Edge
import blue.bluejam.algorithms.kotlin.graph.Graph
import blue.bluejam.algorithms.kotlin.graph.topologicalSort
import blue.bluejam.algorithms.kotlin.testutils.SimpleVerifier
import java.lang.StringBuilder
import java.util.*

// This problem should be verified by a special judge.
// But, since it's working, omit implementing it.
class AojGrl4B: SimpleVerifier(
    problemUrl = "https://onlinejudge.u-aizu.ac.jp/courses/library/5/GRL/1/GRL_4_B"
) {
    override fun solve(sc: Scanner): String {
        val V = sc.nextInt()
        val E = sc.nextInt()
        val g = Graph(V, 0, 0) { a, b -> a + b }

        for (i in 1..E) {
            val s = sc.nextInt()
            val t = sc.nextInt()
            g.edges[s].add(Edge(s, t, 0))
        }

        val sorted = topologicalSort(g).second
        val sb = StringBuilder()

        sorted.forEach { sb.appendln(it) }

        return sb.toString()
    }
}
