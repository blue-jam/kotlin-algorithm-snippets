package bluejam.datastructure

/**
 * A disjoint set data structure with path compression and union by size.
 *
 * Each element is represented by an index (0-indexed).
 */
class UnionFind(n: Long) {
    val size = n.toInt()

    private val parent: Array<Long>
    private val cardinality: Array<Long>

    init {
        parent = Array(size) { -1L }
        cardinality = Array(size) { 1L }
    }

    /**
     * Find a root element of a set `x` belongs to.
     */
    fun find(x: Long): Long {
        return if (parent[x.toInt()] == -1L) {
            x
        } else {
            parent[x.toInt()] = find(parent[x.toInt()])
            parent[x.toInt()]
        }
    }

    /**
     * Unite sets contain two elements `x` and `y`.
     * If `x` and `y` already belong to a same set, nothing happens.
     *
     * @return If `x` and `y` belong to a same set, return false.
     */
    fun unite(x: Long, y: Long): Boolean {
        val u = find(x).toInt()
        val v = find(y).toInt()
        if (u == v) {
            return false
        }

        val newSize = cardinality[v] + cardinality[u]

        if (cardinality[u] < cardinality[v]) {
            parent[u] = v.toLong()
            cardinality[v] = newSize
        } else {
            parent[v] = u.toLong()
            cardinality[u] = newSize
        }

        return true
    }

    /**
     * Check whether `x` and `y` belongs to a same set.
     *
     * @return `true` if `x` and `y` are in a same set.
     */
    fun same(x: Long, y: Long): Boolean {
        return find(x) == find(y)
    }

    /**
     * Get a size of a set `x` belongs to.
     */
    fun getCardinalityOf(x: Long): Long {
        return cardinality[find(x).toInt()]
    }
}
