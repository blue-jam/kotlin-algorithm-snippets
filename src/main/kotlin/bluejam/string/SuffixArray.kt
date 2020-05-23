package bluejam.string

// TODO: Test rank & LCP with bigger data sets
/**
 * A suffix array (SA) constructed with O(n) times where `n` is the length of a string.
 *
 * This implementation of SA contains an empty suffix. That is the first element of SA is always `n`.
 * More precisely, this class adds `\0` (null character) to the end of a string same as lots of articles add `$` to a string.
 * So, if you pass a string contains `\0`, this implementation might fail.
 */
class SuffixArray(val s: String) {
    /**
     * The length of the original string.
     */
    val length = s.length

    /**
     * The suffix array. The `i`-th element indicates the `i`-th smallest suffix starts from `sa[i]`.
     */
    val sa: IntArray

    /**
     * The inverse array of SA. Indicates a suffix starts from `i` is the `rank[i]`-th smallest suffix.
     */
    val rank: IntArray

    /**
     * The longest common prefix array.
     * The length of a common prefix of `i`-th smallest suffix and `i + 1`-th smallest suffix is `lcp[i]`.
     */
    val lcp: IntArray

    companion object {
        private const val LMS = 0
        private const val S_TYPE = 1
        private const val L_TYPE = 2

        private fun construct(s: IntArray): IntArray {
            val count = IntArray(s.max()!! + 2)
            s.forEach { count[it + 1]++ }
            for (i in 1 until count.size) {
                count[i] += count[i - 1]
            }

            val type = IntArray(s.size)
            type[type.size - 1] = S_TYPE
            for (i in s.size - 2 downTo 0) {
                type[i] = if (s[i] > s[i + 1] || s[i] == s[i + 1] && type[i + 1] == L_TYPE) {
                    L_TYPE
                } else {
                    S_TYPE
                }
            }
            val lms = ArrayList<Int>()
            for (i in 1 until s.size) {
                if (type[i] == S_TYPE && type[i - 1] == L_TYPE) {
                    type[i] = LMS
                    lms.add(i)
                }
            }

            val tSa = inducedSort(s, count, type, lms)
            val semiSortedLms = tSa.filter { it >= 0 && type[it] == LMS }
            val id = IntArray(lms.size) { 0 }
            tSa[semiSortedLms[0]] = id[0]
            for (i in 1 until semiSortedLms.size) {
                val a = semiSortedLms[i - 1]
                val b = semiSortedLms[i]

                var same = s[a] == s[b]
                var p = 1
                while (a + p < s.size && b + p < s.size && same) {
                    if (s[a + p] != s[b + p] || type[a + p] != type[b + p]) {
                        same = false
                        break
                    }
                    if (type[a + p] == LMS) {
                        break
                    }
                    p++
                }
                id[i] = id[i - 1]
                if (!same) {
                    id[i]++
                }
                tSa[semiSortedLms[i]] = id[i]
            }

            val sa1 = if (id.last() != id.size - 1) {
                val s1 = lms.map { tSa[it] }.toIntArray()
                construct(s1).map { lms[it] }
            } else {
                semiSortedLms
            }

            return inducedSort(s, count, type, sa1)
        }

        private fun inducedSort(s: IntArray, count: IntArray, type: IntArray, lms: List<Int>): IntArray {
            val sa = IntArray(s.size) { -1 }
            val pointer = count.copyOf()
            for (idx in lms.reversed()) {
                val c = s[idx] + 1
                sa[--pointer[c]] = idx
            }

            count.copyInto(pointer)
            for (i in sa.indices) {
                val idx = sa[i]
                if (idx <= 0 || type[idx - 1] <= S_TYPE) continue
                val c = s[idx - 1]
                sa[pointer[c]++] = idx - 1
            }
            for (i in sa.indices) {
                if (i != 0 && sa[i] > 0 && type[sa[i]] != L_TYPE) {
                    sa[i] = -1
                }
            }
            count.copyInto(pointer)
            for (i in sa.indices.reversed()) {
                val idx = sa[i]
                if (idx <= 0 || type[idx - 1] == L_TYPE) continue
                val c = s[idx - 1] + 1
                sa[--pointer[c]] = idx - 1
            }

            return sa
        }
    }

    init {
        val iS = (s.map { it.toInt() } + 0).toIntArray()
        sa = construct(iS)

        rank = IntArray(length + 1)
        sa.forEachIndexed { i, v ->
            rank[v] = i
        }

        lcp = IntArray(length + 1)
        var h = 0
        lcp[0] = 0
        for (i in s.indices) {
            val j = sa[rank[i] - 1]
            if (h > 0) {
                h--
            }
            while (j + h < length && i + h < length && s[j + h] == s[i + h]) {
                ++h
            }
            lcp[rank[i] - 1] = h
        }
    }
}
