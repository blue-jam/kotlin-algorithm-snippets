package bluejam.graph

import java.util.*

fun <T : Comparable<T>>topologicalSort(g: Graph<T>): Pair<Boolean, Array<Int>> {
    val color = Array(g.n) {0}  // 1 -> visited but order is not fixed yet, 2 -> order is fixed
    val sorted = ArrayList<Int>()

    fun topologicalRecur(v: Int): Boolean {
        color[v] = 1
        for (e in g.edges[v]) {
            when (color[e.to]) {
                1 -> return false
                0 -> if (!topologicalRecur(e.to)) return false
            }
        }

        color[v] = 2
        sorted.add(v)
        return true
    }

    var res = true
    for (v in g.edges.indices) {
        if (color[v] == 0) {
            res = res && topologicalRecur(v)
        }
    }
    sorted.reverse()

    return Pair(res, sorted.toTypedArray())
}
