package blue.bluejam.algorithms.kotlin.graph

import java.util.*

data class Edge<T: Comparable<T>>(val from: Int, val to: Int, val weight: T): Comparable<Edge<T>> {
    constructor(from: Long, to: Long, weight: T) : this(from.toInt(), to.toInt(), weight)

    override fun compareTo(other: Edge<T>) = weight.compareTo(other.weight)
}

class Graph<T: Comparable<T>>(val n: Int, val zero: T, val inf: T, val plus: (T, T) -> T) {
    val edges = Array(n) { ArrayList<Edge<T>>() }
}
