package bluejam.datastructure

class UnionFind(n: Long) {
    val size = n.toInt()

    private val parent: Array<Long>
    private val cardinality: Array<Long>

    init {
        parent = Array(size) { -1L }
        cardinality = Array(size) { 1L }
    }

    fun find(x: Long): Long {
        return if (parent[x.toInt()] == -1L) {
            x
        } else {
            parent[x.toInt()] = find(parent[x.toInt()])
            parent[x.toInt()]
        }
    }

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

    fun same(x: Long, y: Long): Boolean {
        return find(x) == find(y)
    }

    fun getCardinalityOf(x: Long): Long {
        return cardinality[find(x).toInt()]
    }
}
