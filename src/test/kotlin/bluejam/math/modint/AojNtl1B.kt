package bluejam.math.modint

import bluejam.math.ModInt
import bluejam.testutils.createScannerForCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.File
import java.time.Duration
import java.util.*

val MOD = 1000000007L

fun solveAojNtl1B(sc: Scanner): Long {
    val m = sc.nextLong()
    val n = sc.nextLong()
    return ModInt(m, MOD).pow(n).value
}

class AojNtl1B {
    @TestFactory
    fun run(): List<DynamicTest> {
        val directory = File("build/test-cases/aoj/NTL_1_B")

        return directory.listFiles { file -> file.extension == "in" }!!
            .map { DynamicTest.dynamicTest(it.nameWithoutExtension) {
                val (inSc, diffSc) = createScannerForCase(it)
                val res = Assertions.assertTimeout<Long>(Duration.ofSeconds(1)) { solveAojNtl1B(inSc) }

                Assertions.assertEquals(diffSc.nextLong(), res)
            } }
    }
}
