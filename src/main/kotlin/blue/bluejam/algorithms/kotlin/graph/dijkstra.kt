package blue.bluejam.algorithms.kotlin.graph

import java.util.*

inline fun <reified T: Comparable<T>>dijkstra(g: Graph<T>, s: Int): Pair<Array<T>, Array<Int>> {
    val N = g.n
    val dist = Array(N) { g.inf }
    val prev = Array(N) {-1}

    val Q = PriorityQueue<Edge<T>>()

    dist[s] = g.zero
    Q.add(Edge(-2, s, g.zero))

    while(Q.isNotEmpty()) {
        val e = Q.peek()
        val v = e.to
        Q.poll()

        if (prev[v] != -1) continue
        prev[v] = e.from
        for (i in g.edges[v]) {
            val d = g.plus(dist[v], i.weight)
            if (dist[i.to] > d) {
                dist[i.to] = d
                Q.add(Edge(i.from, i.to, d))
            }
        }
    }

    return Pair(dist, prev)
}
