package bluejam.datastructure

/**
 * A disjoint set data structure with path compression and union by size.
 *
 * Each element is represented by an index (0-indexed).
 */
class UnionFind(n: Int) {
    val size = n

    private val parent: Array<Int>
    private val cardinality: Array<Long>

    init {
        parent = Array(size) { -1 }
        cardinality = Array(size) { 1L }
    }

    /**
     * Find a root element of a set `x` belongs to.
     */
    fun find(x: Int): Int {
        return if (parent[x] == -1) {
            x
        } else {
            parent[x] = find(parent[x])
            parent[x]
        }
    }

    /**
     * Unite sets contain two elements `x` and `y`.
     * If `x` and `y` already belong to a same set, nothing happens.
     *
     * @return If `x` and `y` belong to a same set, return false.
     */
    fun unite(x: Int, y: Int): Boolean {
        val u = find(x)
        val v = find(y)
        if (u == v) {
            return false
        }

        val newSize = cardinality[v] + cardinality[u]

        if (cardinality[u] < cardinality[v]) {
            parent[u] = v
            cardinality[v] = newSize
        } else {
            parent[v] = u
            cardinality[u] = newSize
        }

        return true
    }

    /**
     * Check whether `x` and `y` belongs to a same set.
     *
     * @return `true` if `x` and `y` are in a same set.
     */
    fun same(x: Int, y: Int): Boolean {
        return find(x) == find(y)
    }

    /**
     * Get a size of a set `x` belongs to.
     */
    fun getCardinalityOf(x: Int): Long {
        return cardinality[find(x)]
    }
}
