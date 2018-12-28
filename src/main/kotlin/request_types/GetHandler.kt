package request_types

import builders.ResponseHeadBuilder
import parser.ResponceParser
import PageGetter
import java.io.FileNotFoundException

class GetHandler(private val request: ResponceParser) {

    fun createResponse(): String {
        var fileContent = ""
        try {
            fileContent = PageGetter().getPageFromFile(request.getPath())
        } catch (e: FileNotFoundException) {
            return make404resp()
        } finally {
            println("Some unknown error while reading the file (path: ${request.getPath()}) in GetHandler class")
        }

        return ResponseHeadBuilder()
                .okStatus()
                .contentTypeHtmlAndCharsetUtf()
                .contentLen(fileContent.length)
                .connectionClose()
                .build() + fileContent
    }

    private fun make404resp(): String {
        return ResponseHeadBuilder()
                .NotFoundRequestStatus()
                .contentTypeHtmlAndCharsetUtf()
                .connectionClose()
                .build() + "<HTML><BODY>404\nFile not found</BODY></HTML>"
    }
}
