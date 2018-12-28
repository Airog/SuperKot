package parser

class ResponceParser() {

    private var resultArr: List<String> = listOf()
    private var requestType: String = ""
    private var path: String = ""
    private var protocolVersion: String = ""

    constructor(receivedStringPack: String) : this() {
        if (receivedStringPack == "") {
            throw Exception("Error. Package is empty")
        }

        resultArr = receivedStringPack.split(" ")

        //TODO add checker for null or empty parameters
        requestType = resultArr.get(0)
        path = resultArr.get(1)
    }

    fun getAllElements(): List<String>{
        return resultArr
    }

    fun getRequestType(): String {
        return requestType
    }

    fun getPath(): String {
        return path
    }


}