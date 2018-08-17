import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset

class ResponserHttp(private val socket: Socket) {
    private val writer: OutputStream = socket.getOutputStream()


    fun sendResponse(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }
}