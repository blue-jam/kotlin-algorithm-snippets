package blue.bluejam.algorithms.kotlin.testutils

import org.junit.jupiter.api.Assertions.assertEquals
import java.lang.StringBuilder
import java.util.*

abstract class SimpleVerifier(
    problemUrl: String,
    timeLimit: Long = 1
) : Verifier<String>(problemUrl, timeLimit) {
    override fun readAnswerFile(sc: Scanner): String {
        val output = StringBuilder()

        while (sc.hasNextLine()) {
            output.appendln(sc.nextLine())
        }

        return output.toString()
    }

    override fun assertResult(expected: String, actual: String) {
        assertEquals(expected, actual)
    }
}
