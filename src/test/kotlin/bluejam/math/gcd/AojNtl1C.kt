package bluejam.math.gcd

import bluejam.math.lcm
import bluejam.testutils.createScannerForCase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTimeout
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.File
import java.time.Duration
import java.util.*

fun solveAojNtl1C(sc: Scanner): Long {
    val N = sc.nextInt()
    val a = Array(N) { sc.nextLong() }

    return a.fold(1L, ::lcm)
}

class AojNtl1C {
    @TestFactory
    fun run(): List<DynamicTest> {
        val directory = File("build/test-cases/aoj/NTL_1_C")

        return directory.listFiles { file -> file.extension == "in" }!!
            .map { DynamicTest.dynamicTest(it.nameWithoutExtension) {
                val (inSc, diffSc) = createScannerForCase(it)
                val res = assertTimeout<Long>(Duration.ofSeconds(1)) { solveAojNtl1C(inSc) }

                assertEquals(diffSc.nextLong(), res)
            } }
    }
}
