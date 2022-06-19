package blue.bluejam.algorithms.kotlin.graph.sssp

import blue.bluejam.algorithms.kotlin.graph.Edge
import blue.bluejam.algorithms.kotlin.graph.Graph
import blue.bluejam.algorithms.kotlin.graph.dijkstra
import blue.bluejam.algorithms.kotlin.testutils.SimpleVerifier
import java.lang.StringBuilder
import java.util.*

class AojGrl1A : SimpleVerifier(
    problemUrl = "https://onlinejudge.u-aizu.ac.jp/courses/library/5/GRL/1/GRL_1_A",
    timeLimit = 3
) {
    override fun solve(sc: Scanner): String {
        val INF = 1e14.toLong()

        val N = sc.nextInt()
        val M = sc.nextInt()
        val r = sc.nextInt()
        val g = Graph(N, 0L, INF) { a, b -> a + b }

        for (i in 1..M) {
            val s = sc.nextInt()
            val t = sc.nextInt()
            val d = sc.nextLong()

            g.edges[s].add(Edge(s, t, d))
        }

        val dist = dijkstra(g, r).first

        val sb = StringBuilder()
        for (i in dist.indices) {
            sb.appendln(if (dist[i] == INF) "INF" else dist[i])
        }

        return sb.toString()
    }
}
