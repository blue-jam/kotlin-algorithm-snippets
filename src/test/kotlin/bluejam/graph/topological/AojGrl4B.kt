package bluejam.graph.topological

import bluejam.graph.Edge
import bluejam.graph.Graph
import bluejam.graph.topologicalSort
import java.util.*

fun solve(sc: Scanner) {
    val V = sc.nextInt()
    val E = sc.nextInt()
    val g = Graph(V, 0, 0) { a, b -> a + b }

    for (i in 1 .. E) {
        val s = sc.nextInt()
        val t = sc.nextInt()
        g.edges[s].add(Edge(s, t, 0))
    }

    val sorted = topologicalSort(g).second

    sorted.forEach { println(it) }
}

fun main() {
    solve(Scanner(System.`in`))
}
