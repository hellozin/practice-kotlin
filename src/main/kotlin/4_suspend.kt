import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

suspend fun yieldThreeTime() {
    log("1")
    delay(1000L)
    yield()
    log("2")
    delay(1000L)
    yield()
    log("3")
    delay(1000L)
    yield()
    log("4")
}

fun main(args: Array<String>) {
    log("main() started.")
    GlobalScope.launch { yieldThreeTime() }
    log("yieldThreeTime() executed")
    Thread.sleep(4000L)
    log("main() terminated.")
}