package bluejam.graph.sssp

import bluejam.graph.Edge
import bluejam.graph.Graph
import bluejam.graph.bellmanFord
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NegativeCycleDetection {
    @Test
    fun noNegativeCycle() {
        val g = Graph(4, 0L, 1e14.toLong()) { a, b -> a + b }

        val edges = arrayOf(
            Edge(0, 1, -3L),
            Edge(0, 2, -1L),
            Edge(1, 3, -1L),
            Edge(2, 3, -2L),
            Edge(1, 1, 2L)
        )

        edges.forEach { g.edges[it.from].add(it) }

        val res = bellmanFord(g, 0, 3)

        assertFalse(res.third)
        assertEquals(-4L, res.first[3])
    }

    @Test
    fun hasNegativeCycle_affectsResult() {
        // https://youtu.be/1Z6ofKN03_Y?t=6509
        val g = Graph(4, 0L, 1e14.toLong()) { a, b -> a + b }

        val edges = arrayOf(
            Edge(0, 1, 0L),
            Edge(1, 2, 1L),
            Edge(2, 1, -2L),
            Edge(0, 3, -2L),
            Edge(2, 3, 10000L)
        )

        edges.forEach { g.edges[it.from].add(it) }

        val res = bellmanFord(g, 0, 3)

        assertTrue(res.third)
    }

    @Test
    fun hasNegativeCycle_cannotAchieveGoal() {
        val g = Graph(3, 0L, 1e14.toLong()) { a, b -> a + b }

        val edges = arrayOf(
            Edge(0, 1, 0L),
            Edge(1, 1, -2L),
            Edge(0, 2, 1L)
        )

        edges.forEach { g.edges[it.from].add(it) }

        val res = bellmanFord(g, 0, 2)

        assertFalse(res.third)
        assertEquals(1L, res.first[2])
    }

    @Test
    fun hasNegativeCycle_notAchievableFromStart() {
        val g = Graph(3, 0L, 1e14.toLong()) { a, b -> a + b }

        val edges = arrayOf(
            Edge(0, 2, 1L),
            Edge(1, 1, -2L),
            Edge(1, 2, 1L)
        )

        edges.forEach { g.edges[it.from].add(it) }

        val res = bellmanFord(g, 0, 2)

        assertFalse(res.third)
        assertEquals(1L, res.first[2])
    }
}
