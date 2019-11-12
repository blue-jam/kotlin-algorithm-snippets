package bluejam.graph.bridge

import bluejam.graph.Edge
import bluejam.graph.Graph
import bluejam.graph.findBridges
import bluejam.testutils.SimpleVerifier
import java.lang.StringBuilder
import java.util.*

class AojGrl3B: SimpleVerifier("aoj/GRL_3_B") {
    override fun solve(sc: Scanner): String {
        val V = sc.nextInt()
        val E = sc.nextInt()
        val g = Graph(V, 0, 0) { a, b -> a + b }

        for (i in 0 until E) {
            val s = sc.nextInt()
            val t = sc.nextInt()
            g.edges[s].add(Edge(s, t, 0))
            g.edges[t].add(Edge(t, s, 0))
        }

        val bridges = findBridges(g).first
            .sortedWith(Comparator { a, b -> if (a.from == b.from) a.to - b.to else a.from - b.from })
        val sb = StringBuilder()

        for (e in bridges) {
            sb.appendln("${e.from} ${e.to}")
        }

        return sb.toString()
    }
}
