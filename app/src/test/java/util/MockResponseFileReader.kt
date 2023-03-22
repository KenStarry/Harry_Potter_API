package util

import java.io.InputStreamReader

class MockResponseFileReader(
    val path: String
) {
    lateinit var content: String

    init {
        val reader = InputStreamReader(this.javaClass.classLoader!!.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }
}