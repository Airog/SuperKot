import java.io.InputStreamReader
import java.net.Socket

class ConnectionHandler(private val sockConn: Socket) {


    private var running: Boolean = false

    fun run() {
        running = true

        print("sockConn connection for '${sockConn.inetAddress.hostAddress}' started\n")

        var receivedDataString = convertInputDataToString()

//        Logget.logIt(receivedDataString)

        when (calculateRequestType(receivedDataString)) {

            "GET" ->
                ResponserHttp(sockConn).sendResponse(
                        ResponseHeadBuilder()
                                .okStatus()
                                .contentTypeHtmlAndCharsetUtf()
                                //TODO get the symbol count of HTML file
                                //.contentLen(110)
                                .connectionClose()
                                .build()
                                + PageGetter().getPageFromFile()
                )


//            "POST" ->

            //Return 400
            else ->
                ResponserHttp(sockConn).sendResponse(
                        ResponseHeadBuilder()
                                .BadRequestStatus()
                                .build()
                                +
                                "<HTML><BODY>400 Bad Request</BODY></HTML>")

        }

        shutdown()
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