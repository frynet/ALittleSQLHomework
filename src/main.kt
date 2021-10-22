import utils.Generate
import java.io.File
import java.nio.charset.Charset


const val filename = "fill.sql"

fun main() {
    File(filename).writeText(Generate.fileAsString(), Charset.defaultCharset())
}