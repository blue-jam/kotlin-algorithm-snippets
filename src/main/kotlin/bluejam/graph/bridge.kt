package bluejam.graph

import java.util.*

fun <T : Comparable<T>>findBridges(g: Graph<T>): Pair<Array<Edge<T>>, Array<TreeSet<Int>>> {
    val num = Array(g.n) {0}
    val open = Array(g.n) {false}
    val oReps = ArrayDeque<Int>()
    val oNodes = ArrayDeque<Int>()
    var cnt = 0
    val bridges = ArrayList<Edge<T>>()
    val comp = ArrayList<TreeSet<Int>>()

    fun recur(v: Int, u: Int) {
        if (num[v] != 0) {
            if (open[v]) while(num[oReps.first] > num[v]) oReps.removeFirst()
            return
        }

        num[v] = ++cnt
        oReps.addFirst(v)
        oNodes.addFirst(v)
        open[v] = true

        for (e in g.edges[v]) if (e.to != u) {
            recur(e.to, e.from)
        }

        if (v == oReps.first) {
            oReps.removeFirst()
            comp.add(TreeSet())

            do {
                val w = oNodes.first
                oNodes.removeFirst()
                open[w] = false
                comp.last().add(w)
            } while (v != w)
            bridges.add(Edge(Math.min(u, v), Math.max(u, v), g.zero))
        }
    }

    for (i in 0 until g.n) if (num[i] == 0) {
        recur(i, g.n)
        bridges.removeAt(bridges.size - 1)
    }

    return Pair(bridges.toTypedArray(), comp.toTypedArray())
}
