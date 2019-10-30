package bluejam.graph

inline fun <reified T: Comparable<T>>bellmanFord(g: Graph<T>, s: Int): Triple<Array<T>, Array<Int>, Array<Boolean>> {
    val N = g.n
    val dist = Array(N) { g.inf }
    val prev = Array(N) {-1}

    dist[s] = g.zero

    for (k in 1 .. N) {
        for (v in 0 until N) {
            for (e in g.edges[v]) {
                if (dist[e.from] != g.inf && dist[e.to] > g.plus(dist[e.from], e.weight)) {
                    dist[e.to] = g.plus(dist[e.from], e.weight)
                    prev[e.to] = e.from
                }
            }
        }
    }

    val dist2 = dist.copyOf()
    for (k in 1 .. N) {
        for (v in 0 until N) {
            for (e in g.edges[v]) {
                if (dist2[e.from] != g.inf && dist2[e.to] > g.plus(dist2[e.from], e.weight)) {
                    dist2[e.to] = g.plus(dist2[e.from], e.weight)
                }
            }
        }
    }

    val affectedByNegativeLoop = Array(N) { idx -> dist[idx] != dist2[idx] }

    return Triple(dist, prev, affectedByNegativeLoop)
}
