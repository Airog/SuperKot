import java.io.File
import java.io.InputStream

class PageGetter {

    private val rootPath: String = "D:/rootik/"

    fun getPageFromFile(path: String): String {
        //TODO add file finder method
        val inputStream: InputStream = File(rootPath + path).inputStream()
        val inputString = inputStream.bufferedReader().use { it.readText() }

        //do we need to close inputStream or it will be closed automatically?

        return inputString
    }
}