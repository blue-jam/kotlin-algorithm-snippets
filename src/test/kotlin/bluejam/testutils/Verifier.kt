package bluejam.testutils

import org.apache.commons.codec.digest.DigestUtils
import org.junit.jupiter.api.Assertions.assertTimeout
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.File
import java.time.Duration
import java.util.*

abstract class Verifier<T>(
    private val problemUrl: String,
    private val timeLimit: Long = 1
) {
    abstract fun solve(sc: Scanner): T
    abstract fun readAnswerFile(sc: Scanner): T
    abstract fun assertResult(expected: T, actual: T)

    @TestFactory
    fun run(): List<DynamicTest> {
        val directory = File("build/test-cases/${DigestUtils.md5Hex(problemUrl)}")

        return directory.listFiles { file -> file.extension == "in" }!!
            .map {
                DynamicTest.dynamicTest(it.nameWithoutExtension) {
                    val (inSc, diffSc) = createScannerForCase(it)
                    val res = assertTimeout<T>(Duration.ofSeconds(timeLimit)) { solve(inSc) }

                    assertResult(readAnswerFile(diffSc), res)
                }
            }
    }
}
