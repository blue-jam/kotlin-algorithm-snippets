package bluejam.testutils

import java.io.File
import java.util.*

fun createScannerForCase(inputFile: File): Pair<Scanner, Scanner>
        = Pair(Scanner(inputFile), Scanner(File(inputFile.parent, "${inputFile.nameWithoutExtension}.out")))
