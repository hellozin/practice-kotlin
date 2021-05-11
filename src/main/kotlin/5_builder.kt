import generator.GeneratorBuilder
import generator.GeneratorCoroutine
import generator.generate
import kotlin.coroutines.RestrictsSuspension
import kotlin.coroutines.startCoroutine

fun idMaker() = generate<Int, Unit> {
    var index = 0
    while (index < 3) {
        yield(index++)
    }
}

fun main(args: Array<String>) {
    val gen = idMaker()
    println(gen.next(Unit))
    println(gen.next(Unit))
    println(gen.next(Unit))
    println(gen.next(Unit))
}
//
//// next(T)를 호출해 R타입의 값을 반환해야 한다.
//interface Generator<out R, in T> {
//    fun next(param: T): R?  // 제너레이터가 끝나면 null 반환 필요
//}
//
//// R 타입을 받아 Unit을 돌려준다. (yield로 값을 돌려주기때문에 다른 값은 반환할 필요가 없다.)
//// generate의 매개변수로 받는 블럭에서 yield()를 호출할 수 있어야 한다. -> 수신 객체 지정 람다
////      GeneratorBuilder를 만들어 yield()를 가지고 있는 클래스를 this로 포함시키자.
//
//@RestrictsSuspension // suspend가 붙은 함수에만 이 클래스를 수신 객체로 지정할 수 있게 한다.
//interface GeneratorBuilder<in T, R> {
//    suspend fun yield(value: T): R
//    suspend fun yieldAll(generator: Generator<T, R>, param: R)
//}
//
//fun <T, R> generate(block: suspend GeneratorBuilder<T, R>.(R) -> Unit): generator.Generator<T, R> {
//    val coroutine = GeneratorCoroutine<T, R>()
//    val initial: suspend (R) -> Unit = { result -> block(coroutine, result) }
//    coroutine.nextStep = { param -> initial.startCoroutine(param, coroutine) }
//    return coroutine
//}

