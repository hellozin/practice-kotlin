import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

//public fun CoroutineScope.launch(
//    context: CoroutineContext = EmptyCoroutineContext,
//    start: CoroutineStart = CoroutineStart.DEFAULT,
//    block: suspend CoroutineScope.() -> Unit
//): Job {
//    val newContext = newCoroutineContext(context)
//    val coroutine = if (start.isLazy)
//        LazyStandaloneCoroutine(newContext, block) else
//        StandaloneCoroutine(newContext, active = true)
//    coroutine.start(start, coroutine, block)
//    return coroutine
//}

//public fun <T> CoroutineScope.async(
//    context: CoroutineContext = EmptyCoroutineContext,
//    start: CoroutineStart = CoroutineStart.DEFAULT,
//    block: suspend CoroutineScope.() -> T
//): Deferred<T> {
//    val newContext = newCoroutineContext(context)
//    val coroutine = if (start.isLazy)
//        LazyDeferredCoroutine(newContext, block) else
//        DeferredCoroutine<T>(newContext, active = true)
//    coroutine.start(start, coroutine, block)
//    return coroutine
//}

fun sumAll() {
    runBlocking {
        val d1 = async { delay(1000L); 1 }
        log("after async(d1)")
        val d2 = async { delay(2000L); 2 }
        log("after async(d2)")
        val d3 = async { delay(3000L); 3 }
        log("after async(d3)")

        log("1+2+3 = ${d1.await() +d2.await() +d3.await()}")
        log("after await all & add")
    }
}

fun main(args: Array<String>) {
    sumAll()
}

/*
* - async는 사실상 launch와 같은 일을 한다.
* - 다만 async는 Job 대신 Deffered를 반환한다.
* - 모든 async 함수들이 메인 스레드에서 실행된다.
* */