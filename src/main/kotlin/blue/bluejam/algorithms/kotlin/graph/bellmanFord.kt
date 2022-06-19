package blue.bluejam.algorithms.kotlin.graph

import java.util.*

inline fun <reified T: Comparable<T>>bellmanFord(g: Graph<T>, s: Int): Triple<Array<T>, Array<Int>, Boolean> {
    val N = g.n
    val dist = Array(N) { g.inf }
    val prev = Array(N) {-1}
    var hasNegativeLoop = false

    dist[s] = g.zero

    for (k in 1 .. N + 1) {
        for (v in 0 until N) {
            for (e in g.edges[v]) {
                if (dist[e.from] != g.inf && dist[e.to] > g.plus(dist[e.from], e.weight)) {
                    dist[e.to] = g.plus(dist[e.from], e.weight)
                    prev[e.to] = e.from
                    hasNegativeLoop = k == N + 1
                }
            }
        }
    }

    return Triple(dist, prev, hasNegativeLoop)
}

fun <T : Comparable<T>>listRequiredVertices(g: Graph<T>, s: Int, t: Int): Array<Boolean> {
    fun listReachable(g: Graph<T>, s: Int): Array<Boolean> {
        val reachable = Array(g.n) { false }
        val Q = ArrayDeque<Int>()

        reachable[s] = true
        Q.addLast(s)

        while (Q.isNotEmpty()) {
            val v = Q.pollFirst()

            for (e in g.edges[v]) {
                if (reachable[e.to]) continue
                reachable[e.to] = true
                Q.addLast(e.to)
            }
        }

        return reachable
    }

    val reversedGraph = Graph(g.n, g.zero, g.inf, g.plus)
    for (v in 0 until g.n) {
        for (e in g.edges[v]) {
            reversedGraph.edges[e.to].add(Edge(e.to, e.from, e.weight))
        }
    }

    val reachable = listReachable(g, s)
    val reverseReachable = listReachable(reversedGraph, t)

    return reachable.indices.map { reachable[it] && reverseReachable[it] }.toTypedArray()
}

/**
 * Calculate distance from `s` to `t` on a graph `g`.
 * If there exists an negative loop on a path from `s` to `t`, put `true` to the third value of a tuple.
 */
inline fun <reified T : Comparable<T>>bellmanFord(g: Graph<T>, s: Int, t: Int): Triple<Array<T>, Array<Int>, Boolean> {
    val vs = listRequiredVertices(g, s, t)

    val refinedGraph = Graph(g.n, g.zero, g.inf, g.plus)

    for (v in 0 until g.n) {
        for (e in g.edges[v]) {
            if (vs[e.from] && vs[e.to]) {
                refinedGraph.edges[e.from].add(e)
            }
        }
    }

    return bellmanFord(refinedGraph, s)
}
