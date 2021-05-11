import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

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

fun main(args: Array<String>) {
    runBlocking {
        // 부모 컨텍스트(이 경우 main)를 사용
        launch {
            println("main runBlocking   : I'm working in thread ${Thread.currentThread().name}")
        }
        // 특정 스레드에 종속되지 않음 ? 메인 스레드 사용
        launch(Dispatchers.Unconfined) {
            println("Unconfined         : I'm working in thread ${Thread.currentThread().name}")
        }
        // 기본 디스패처 사용
        launch(Dispatchers.Default) {
            println("Default            : I'm working in thread ${Thread.currentThread().name}")
        }
        // 새 스레드 사용
        launch(newSingleThreadContext("MyThread")) {
            println("MyThread           : I'm working in thread ${Thread.currentThread().name}")

            launch(Dispatchers.Unconfined) {
                println("Unconfined in MyThread : I'm working in thread ${Thread.currentThread().name}")
            }
        }
    }
}