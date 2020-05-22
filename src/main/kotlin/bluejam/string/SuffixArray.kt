package bluejam.string

// TODO: Speed up :(
class SuffixArray(val s: String) {
    val length = s.length
    val sa = IntArray(length + 1)
    val rank: IntArray
    val lcp = IntArray(length + 1)

    init {
        val tmp = Array(length + 1) { Triple(0, 0, 0) }
        val trank = IntArray(length + 1)

        rank = IntArray(length + 1) { if (it < length) s[it].toInt() else -1 }

        var k = 1
        while (k <= length) {
            for (i in tmp.indices) {
                tmp[i] = Triple(rank[i], if (i + k < length) rank[i + k] else -1, i)
            }

            tmp.sortWith(Comparator { a, b ->
                if (a.first != b.first) a.first - b.first
                else if (a.second != b.second) a.second - b.second
                else a.third - b.third
            })
            trank[tmp[0].third] = 0

            for (i in 1..length) {
                trank[tmp[i].third] = trank[tmp[i - 1].third]

                if (tmp[i].first != tmp[i - 1].first || tmp[i].second != tmp[i - 1].second) {
                    trank[tmp[i].third]++
                }
            }
            trank.forEachIndexed { i, v ->
                rank[i] = v
            }
            k *= 2
        }

        for (i in rank.indices) {
            sa[rank[i]] = i
        }

        // LCP
        // TODO: Test
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
