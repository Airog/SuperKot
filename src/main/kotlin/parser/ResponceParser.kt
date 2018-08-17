package parser

class ResponceParser() {

    private var requestType: String = ""
    private var path: String = ""
    private var protocolVersion: String = ""

    constructor(receivedStringPack: String) : this() {
        if (receivedStringPack == "") {
            throw Exception("Error. Package is empty")
        }

        val resultArr: List<String> = receivedStringPack.split(" ")

        //TODO add checker for null or empty parameters
        requestType = resultArr.get(0)
        path = resultArr.get(1)
    }

    fun getRequestType(): String {
        return requestType
    }

    fun getPath(): String {
        return path
    }


}