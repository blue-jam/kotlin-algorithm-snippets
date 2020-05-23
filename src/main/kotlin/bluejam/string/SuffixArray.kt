package bluejam.string

class SuffixArray(val s: String) {
    val length = s.length
    val sa: IntArray
    val rank: IntArray
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

        // LCP
        // TODO: Test
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
