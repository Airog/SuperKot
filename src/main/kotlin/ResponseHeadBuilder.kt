class ResponseHeadBuilder {

    private var result: String = ""

    fun build(): String {
        result += "\n"
        print("build result:\n$result")
        return result
    }

    fun okStatus(): ResponseHeadBuilder {
        result += "HTTP/1.1 200 OK\n"
        return this
    }

    fun BadRequestStatus(): ResponseHeadBuilder {
        result += "HTTP/1.1 400 Bad Request\n"
        return this
    }

    fun contentTypeHtmlAndCharsetUtf(): ResponseHeadBuilder {
        result += "Content-Type: text/html; charset=utf-8\n"
        return this
    }

    fun contentLen(len: Int): ResponseHeadBuilder {
        result += "Content-Length: $len\n"
        return this
    }

    fun connectionClose(): ResponseHeadBuilder {
        result += "Connection: close\n"
        return this
    }

    fun connectionKeepAlive(): ResponseHeadBuilder {
        result += "Connection: keep-alive\n"
        return this
    }

}