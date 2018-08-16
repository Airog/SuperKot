import java.io.InputStreamReader
import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset

class ConnectionHandler(private val sockConn: Socket) {

    private val writer: OutputStream = sockConn.getOutputStream()
    private var running: Boolean = false

    fun run() {
        running = true

        print("sockConn connection for '${sockConn.inetAddress.hostAddress}' started\n")

        var receivedDataString = convertInputDataToString()

        println(receivedDataString)

        when (calculateRequestType(receivedDataString)) {

            "GET" ->
                write(
                        ResponseHeadBuilder()
                                .okStatusHead()
                                .contentTypeHtmlAndCharsetUtf()
                                //TODO get the symbol count of HTML file
                                //.contentLen(110)
                                .connectionClose()
                                .build()
                                + PageGetter().getPageFromFile()
                )

//            "POST" ->

//            else ->

        }


    }

    private fun write(message: String) {
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
    }

    private fun shutdown() {
        running = false
        sockConn.close()
        println("${sockConn.inetAddress.hostAddress} closed the connection")
    }

    private fun convertInputDataToString(): String {
        val reader = InputStreamReader(sockConn.getInputStream())
        var buf: String? = "";
        while (reader.ready()) {
            try {

                buf += reader.read().toChar()
            } catch (ex: Exception) {
                // TODO: Implement exception handling
                shutdown()
            } finally {

            }
        }
        return buf ?: ""
    }

    private fun calculateRequestType(receivedStringPack: String): String {
        if (receivedStringPack == "") {
            return ""
        }

        val resultArr: List<String> = receivedStringPack.split(" ")
        return resultArr.get(0)

    }

}