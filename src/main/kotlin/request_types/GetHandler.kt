package request_types

class GetWorker() {

    constructor(request: List<String>) : this() {
        ResponserHttp(sockConn).sendResponse(
                ResponseHeadBuilder()
                        .okStatus()
                        .contentTypeHtmlAndCharsetUtf()
                        //TODO get the symbol count of HTML file
                        //.contentLen(110)
                        .connectionClose()
                        .build()
                        + PageGetter().getPageFromFile(parsedPack.getPath())
        )
    }

}