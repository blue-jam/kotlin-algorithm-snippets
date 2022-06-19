package blue.bluejam.algorithms.kotlin.datastructure

/**
 * Segment Tree that manages a list of `T`.
 *
 * @param n The length of list that SegmentTree manages.
 * @param e An identity element of `T`
 * @param op A binary operation on `T`.
 */
class SegmentTree<T>(
    n: Int,
    val e: T,
    val op: (T, T) -> T
) {
    val size: Int
    // TODO: Understand Kotlin to figure out the way to make this an Array
    private val data: MutableList<T>

    init {
        var t = 1
        while (t < n) {
            t *= 2
        }
        size = t

        data = MutableList(size * 2 - 1) { e }
    }

    /**
     * Initialize the Segment Tree with values in `initList`.
     * O(n)
     * @param initList: List of initial values
     */
    fun initialize(initList: List<T>) {
        // TODO: Simplify this with data.mapIndexed for performance
        data.fill(e)
        initList.forEachIndexed { i, t -> data[i + size - 1] = t }

        for (k in size - 1 downTo 0) {
            data[k] = op(data[k * 2 + 1], data[k * 2 + 2])
        }
    }

    /**
     * Update a value at `index` with `v`.
     */
    fun update(index: Int, v: T) {
        var k = index + size - 1
        data[k] = v
        while (k > 0) {
            k = (k - 1) / 2
            data[k] = op(data[k * 2 + 1], data[k * 2 + 2])
        }
    }

    private fun lookup(a: Int, b: Int, k: Int, l: Int, r: Int): T {
        return if (b <= l || r <= a) {
            e
        } else if (a <= l && r <= b) {
            data[k]
        } else {
            val vLeft = lookup(a, b, k * 2 + 1, l, (l + r) / 2)
            val vRight = lookup(a, b, k * 2 + 2, (l + r) / 2, r)
            op(vLeft, vRight)
        }
    }

    /**
     * Query [a, b)
     */
    fun query(a: Int, b: Int): T = lookup(a, b, 0, 0, size)
}
