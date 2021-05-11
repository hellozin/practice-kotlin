import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

fun now(): LocalTime = ZonedDateTime.now().toLocalTime().truncatedTo(ChronoUnit.MILLIS)

fun log(msg: String) = println("${now()}:${Thread.currentThread()}: $msg")

fun launchInGlobalScope() {
    GlobalScope.launch {
        log("coroutine started.")
    }
}

fun runBlockingExample() {
    runBlocking {
        launch {
            log("GlobalScope.launch started.")
        }
    }
}

fun yieldExample() {
    runBlocking {
        launch {
            log("1")
            yield()
            log("3")
            yield()
            log("5")
        }
        log("after first launch")
        launch {
            log("2")
            delay(1000L)
            log("4")
            delay(1000L)
            log("6")
        }
        log("after second launch")
    }
}

fun example1() {
    log("main() started.")
    launchInGlobalScope()   // main 스레드가 아닌 다른 스레드에서 실행된다.
    log("launchInGlobalScope() executed")
    Thread.sleep(5000L) // 이 코드가 없으면 main 스레드가 종료되면서 전체 프로그램이 종료된다.
    log("main() terminated.")
}

fun example2() {
    log("main() started.")
    runBlockingExample()
    log("runBlockingExample() executed")
    log("main() terminated.")
}

fun example3() {
    log("main() started.")
    yieldExample()
    log("yieldExample() executed")
    log("main() terminated.")
}

fun main(args: Array<String>) {
//    example1()
//    example2()
    example3()
}

/*
* - launch는 즉시 반환된다.
* - runBlocking은 내부 코루틴이 모두 끝난 다음에 반환된다.
* - delay()를 사용하면 그 시간이 지날 때 가지 다른 코루틴에게 실행을 양보한다.
* */