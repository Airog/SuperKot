import java.io.File
import java.io.InputStream

class PageGetter {

    private val rootPath: String = "D:/rootik/"

    fun getPageFromFile(): String {
        val inputStream: InputStream = File(rootPath + "index.html").inputStream()
        val inputString = inputStream.bufferedReader().use { it.readText() }

        return inputString
    }
}