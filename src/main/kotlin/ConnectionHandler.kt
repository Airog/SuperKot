import builders.ResponseHeadBuilder
import java.io.InputStreamReader
import java.net.Socket
import parser.ResponceParser
import request_types.GetHandler

class ConnectionHandler(private val sockConn: Socket) {


    private var running: Boolean = false

    fun run() {
        running = true

        print("sockConn connection for '${sockConn.inetAddress.hostAddress}' started\n")

        var receivedDataString = convertInputDataToString()
        logger.Logger().logIt(receivedDataString)
        var parsedPack = ResponceParser(receivedDataString)

        when (parsedPack.getRequestType()) {
            "GET" ->
                sendResponse(GetHandler(parsedPack).createResponse())

            "POST" ->
                sendResponse("POST is't working yet")

            else ->
                sendResponse(make400resp())
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

    private fun make400resp() : String {
        return ResponseHeadBuilder()
                        .BadRequestStatus()
                        .build() + "<HTML><BODY>400 Bad Request</BODY></HTML>"
    }

    private fun sendResponse(resp: String){
        ResponserHttp(sockConn).sendResponse(resp)
    }

}