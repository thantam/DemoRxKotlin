package com.example.kotlindemo

import android.annotation.SuppressLint
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random


class DemoClass {
}

@SuppressLint("CheckResult")
fun main(args: Array<String>) {
/*==================================== operator create observable ===============================*/
//    Observable.create<Int> {
//        it.onNext(1)
//        it.onNext(2)
//        it.onNext(3)
//        it.onNext(4)
//        it.onNext(5)
////        it.onError(Throwable("Error occur"))
//        it.onComplete()
//    }.subscribe(
//        { println(it) },
//        { println("Error ") },
//        { println("Completeeee") }
//    )

/*------------------------- from ------------------------------*/
//    Observable.fromIterable(listOf("String 1", "String 2", "String 4"))
//        .subscribe(
//        { println(it) },
//        { println("Error ") },
//        { println("Completeeee") }
//    )

/*------------------------- just -------------------------- */
//    Observable.just(listOf("String 1", "String 2", "String 4"))
//        .subscribe(
//        { println(it) },
//        { println("Error ") },
//        { println("Completeeee") }
//    )

/* ------------------------ rang -------------------------*/
//    Observable.range(1, 5)
//        .subscribe(
//        { println(it) },
//        { println("Error ") },
//        { println("Completeeee") }
//    )
/*------------------------- defer -------------------------------*/
//    var text = MyItem3(123)
//
//    val justObservable = Observable.just(text)
//    val deferObservable = Observable.defer { Observable.just(text) }
//
//    text = MyItem3(3454)
//
//    justObservable.subscribe { println("just onNext $text") }
//    deferObservable.subscribe { println("defer onNext $text") }

/*=================================== Observer ============================================*/
//    val observable = listOf(1, 2, 4, 5, 8).toObservable()
//
//    lateinit var disposable : Disposable
//    val observer = object : Observer<Int> {
//        override fun onComplete() { println("Complete!")}
//        override fun onNext(item: Int) {
//            println("$item")
//            if (item == 5) {
//                disposable.dispose()
//                println("Disposed")
//            }
//        }
//        override fun onError(e: Throwable) {}
//        override fun onSubscribe(d: Disposable) {
//            disposable = d
//        }
//    }
//
//    observable.subscribe(observer)

/*============================== Sample Flowables ================================================*/

//    Observable.range(1, 1000)
//        .map { MyItem3(it) }
//        .observeOn(Schedulers.computation())
//        .subscribe({
//            print("Received $it;\n")
//            runBlocking { delay(50) }
//        }, { it.printStackTrace() })
//    runBlocking { delay(60000) }

//    Flowable.range(1,1000)
//        .map { MyItem3(it) }
//        .observeOn(Schedulers.io())
//        .subscribe({
//            println("Received $it\n")
//            runBlocking { delay(50) }
//        },{it.printStackTrace()})
//    runBlocking { delay(60000) }


/*==================== Sample send number of request item to Flowables ========================== */
//    Flowable.range(1, 15)
//        .map { MyItem3(it) }
//        .observeOn(Schedulers.io())
//        .subscribe(object : Subscriber<MyItem3> {
//            lateinit var subscription: Subscription
//            override fun onSubscribe(sub: Subscription) {
////                subscription = sub
////                subscription.request(5)
//            }
//
//            override fun onNext(s: MyItem3?) {
//                runBlocking { delay(50) }
//                println("Subscriber received " + s!!)
////                if (s.id == 2){
////                    subscription.request(3)
////                }
//            }
//
//            override fun onError(e: Throwable) {
//                e.printStackTrace()
//            }
//
//            override fun onComplete() {
//                println("Done!")
//            }
//        })
//    runBlocking { delay(60000) }


/*================================ Create Flowable ===================================*/

//    val subscriber: Subscriber<Int> = object : Subscriber<Int> {
//        override fun onComplete() {
//            println("All Completed")
//        }
//
//        override fun onNext(item: Int) {
//            println("Next $item")
//        }
//
//        override fun onError(e: Throwable) {
//            println("Error Occured ${e.message}")
//        }
//
//        override fun onSubscribe(subscription: Subscription) {
//            println("New Subscription ")
//            subscription.request(10)
//        }
//    }
//    val flowable: Flowable<Int> = Flowable.create<Int>({
//        for (i in 1..10) {
//            it.onNext(i)
//        }
//        it.onComplete()
//    }, BackpressureStrategy.BUFFER)
//
//
//    flowable
//        .observeOn(Schedulers.io())
//        .subscribe(subscriber)
//    runBlocking { delay(10000) }

/*======================== Create Flowables from Observable ======================================*/
//    val source = Observable.range(1, 200)
//    source.toFlowable(BackpressureStrategy.BUFFER)
//        .map { MyItem3(it) }
//        .observeOn(Schedulers.io())
//        .subscribe{
//            print("Rec. $it;\t")
//            runBlocking { delay(1000) }
//        }
//    runBlocking { delay(100000) }

/*==================================== debound ===================================================*/
//    createObservable()//(1)
//        .debounce(200, TimeUnit.MILLISECONDS)//(2)
//        .subscribe {
//            println(it)//(3)
//        }

/*=================================== distinct ====================================================*/

//    Observable.fromIterable(listOf(1,2,2,3,4,5,5,5,6,7,8,9,3,10))//(1)
//        .distinct()
////        .distinctUntilChanged() //  discards only consecutive duplicate emissions
//        .subscribe { println("Received $it") }

/*===================================== Filter ===================================================*/

//    Observable.fromIterable(listOf(MyItem3(1), MyItem3(4), MyItem3(6), MyItem3(2)))
//        .filter{it.id == 2}
//
////        .ignoreElements() //  listen only on the onComplete of a producer
//        .subscribe{
//            println("Received $it")
//        }

/*================================== The first and last operator =================================*/
//    val observable = Observable.range(1,10)
//    observable.first(0)
////            .last(2)
////
//        .subscribe { item -> println("Receive $item") }


/*==================================== The transforming operators ================================*/
/*============================================= map ==============================================*/
//    val observable = Observable.fromIterable(listOf(10, 9, 8, 7, 6, 5, 4, 3, 2, 1))
//    observable
//        .map {number -> "Transforming Int to String $number" }
//        .subscribe { item -> println("Received $item") }

/*====================================== flatMap =================================================*/
//    val observable = listOf(10,9,8,7,6,5,4,3,2,1).toObservable()
//    observable.flatMap { number ->
//        Observable.create<String> {
//            it.onNext("The Number $number")
//            it.onNext("number/2 ${number / 2}")
//            it.onNext("number%2 ${number % 2}")
//            it.onComplete()
//        }.delay(Random.nextLong(300), TimeUnit.MILLISECONDS)
//    }.subscribe(
//        { item -> println("Received $item") },
//        { t -> println("Received $t") },
//        { println("Complete") })
//
//    runBlocking { delay(10000) }


/*======================= defaultIfEmpty && switchIfEmpty ========================================*/
//    Observable.range(0,10)
//        .filter{it>15}
////        .startWith(-1)
//        .defaultIfEmpty(15)
////        .switchIfEmpty(Observable.range(11,10))
//        .subscribe{
//            println("Received $it")
//        }

/*==================================== sort ==============================================*/
    // can cause OutOfMemory Error
//    Observable.fromIterable(listOf(2,6,7,1,3,4,5,8,10,9))
//        .sorted { item1, item2 -> if (item1 > item2) -1 else 1 }
//        .subscribe { println("Received $it") }
/*============================== Combining producers ============================================*/
    /*============================ startWith ============================*/
//    Observable.range(5,10)
//        .startWith(Observable.just(1,2,3,4))
//        .subscribe {
//            println("Received $it")
//        }

    /*============================ zip ========================================*/
//    val observable1 = Observable.range(1,10)
//    val observable2 = Observable.range(1, 12)
//    Observable.zip(observable1, observable2, io.reactivex.functions.BiFunction<Int, Int, Int>
//    { emissionO1, emissionO2 -> emissionO1 + emissionO2 })
//        .subscribe { println("Received $it") }
//    zipWith
    /*=================================  combineLatest =====================================*/
//        val observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
//        val observable2 = Observable.interval(250, TimeUnit.MILLISECONDS)
//        Observable.combineLatest(observable1,observable2, BiFunction { t1:Long, t2:Long -> "t1: $t1, t2: $t2" })
//                .subscribe{
//                        println("Received $it")
//                }
//        runBlocking { delay(1100) }
    /*======================================== merge ==========================================*/
        // no order, support 4 observable, if over 4 -> use mergeArray
//        val observable1 = Observable.fromIterable(listOf("Kotlin", "Scala", "Groovy"))
//        val observable2 = Observable.fromIterable(listOf("Python", "Java", "C++", "C"))
//        Observable.merge(observable1,observable2)//(1)
//                .subscribe {
//                        println("Received $it")
//                }
    /*================================ concat =========================================*/
        // respect the prescribed ordering: one observable at a time
//        val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS)
//                .take(2)
//                .map { "Observable 1 $it" }
//        val observable2 = Observable.interval(100,
//                TimeUnit.MILLISECONDS).map { "Observable 2 $it" }
//
//        Observable
//                .concat(observable1,observable2)
//                .subscribe {
//                        println("Received $it")
//                }
//        runBlocking { delay(1500) }

 /*===================================== concatMap && flatMap ======================================*/
//        concatMap -> concat
//        flatMap -> merge
//        Observable.range(1,10)
//                .concatMap {
//                        val randDelay = Random().nextInt(10)
//                        return@concatMap Observable.just(it)
//                                .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)//(1)
//                }
//                .blockingSubscribe {
//                        println("Received $it")
//                }
//
//        Observable.range(1,10)
//                .flatMap {
//                        val randDelay = Random().nextInt(10)
//                        return@flatMap Observable.just(it)
//                                .delay(randDelay.toLong(),TimeUnit.MILLISECONDS)//(1)
//                }
//                .blockingSubscribe {
//                        println("Received $it")
//                }

/*===================================== switchMap ============================================*/
//        println("Without delay")
//        Observable.range(1,10)
//                .switchMap {
//                        val randDelay = Random().nextInt(10)
//                        return@switchMap Observable.just(it)
//                }
//                .blockingSubscribe {
//                        println("Received $it")
//                }
//        println("With delay")
//        Observable.range(1,10)
//                .switchMap {
//                        val randDelay = Random().nextInt(10)
//                        return@switchMap Observable.just(it)
//                                .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)//(2)
//                }
//                .blockingSubscribe {
//                        println("Received $it")
//                }




/*============================ The error handling operators ===================================*/
//    Observable.just(1,2,3,4,5)
//        .map { it/(3-it) }
//        .subscribe {
//            println("Received $it")
//        }


//       val observer1 = object : Observer<Int> {
//           override fun onComplete() {}
//           override fun onSubscribe(d: Disposable) {}
//           override fun onNext(t: Int) {println("1 Received $t")}
//           override fun onError(e: Throwable) {}
//       }
//
//    val observer2 = object : Observer<Int> {
//           override fun onComplete() {}
//           override fun onSubscribe(d: Disposable) {}
//           override fun onNext(t: Int) {println("2 Received $t")}
//           override fun onError(e: Throwable) {}
//       }
//
//    val observable = Observable.just(1,2,3,4,5)
//
//    observable.subscribe(observer1)
//    observable.subscribe(observer2)

//onErrorResumeNext
// onErrorReturn
// retry

//        Observable.just(1,2,3,4,5)
//                .map { it/(3-it) }
//                .onErrorReturn { -1 }
//                .subscribe {
//                        println("Received $it")
//                }

//        Observable.just(1,2,3,4,5)
//                .map { it/(3-it) }
//                .onErrorResumeNext(Observable.range(10,5))//(1)
//                .subscribe {
//                        println("Received $it")
//                }

//        Observable.just(1, 2, 3, 4, 5)
//                .map { it / (3 - it) }
//                .retry(3)//(1)
//                .subscribe(
//                        { println("Received $it") },
//                        { println("Error") }
//                )

/*==================================== Scheduler.computation ====================================*/
//    Observable.range(1,10)
//        .subscribe {
//            runBlocking { delay(200) }
//            println("Observable1 Item Received $it")
//        }
//    Observable.range(21,10)
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Observable2 Item Received $it")
//        }

    // run in Schedulers.io
//    Observable.range(1, 10)
//        .subscribeOn(Schedulers.computation())//(1)
//        .subscribe {
//            runBlocking { delay(200) }
//            println("Observable1 Item Received $it")
//        }
//    Observable.range(21, 10)
//        .subscribeOn(Schedulers.computation())//(2)
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Observable2 Item Received $it")
//        }
//    runBlocking { delay(2100) }

/*=================================== Schedulers.trampoline() & Single============================*/
//        Observable.range(1, 10)
//            .subscribeOn(Schedulers.trampoline())//(1)
//            .subscribe {
//                runBlocking { delay(200) }
//                println("Observable1 Item Received $it")
//            }
//        Observable.range(21, 10)
//            .subscribeOn(Schedulers.trampoline())//(2)
//            .subscribe {
//                runBlocking { delay(100) }
//                println("Observable2 Item Received $it")
//            }
//
//        for (i in 1..10) {
//            runBlocking{delay(100)}
//            println("Calling Thread $i")
//        }
//
//    runBlocking { delay(6000) }
/*======================================== Schedulers.from =======================================*/
//    val executor: Executor = Executors.newFixedThreadPool(3)//(1)
//    val scheduler: Scheduler = Schedulers.from(executor)//(2)
//    Observable.range(1, 10)
//        .subscribeOn(scheduler)//(3)
//        .subscribe {
//            runBlocking { delay(200) }
//            println("Observable1 Item Received $it -${Thread.currentThread().name}")
//        }
//    Observable.range(21, 10)
//        .subscribeOn(scheduler)//(4)
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Observable2 Item Received $it -${Thread.currentThread().name}")
//        }
//    Observable.range(51, 10)
//        .subscribeOn(scheduler)//(5)
//        .subscribe {
//            runBlocking { delay(100) }
//            println("Observable3 Item Received $it -${Thread.currentThread().name}")
//        }
//    runBlocking { delay(10000) }//(6)

/*==================================== subscribeOn =============================================*/
    // default
//    println("Calling thread ${Thread.currentThread().name}")
//    Observable.fromIterable(listOf("1","2","3","4","5"))
//        .map {
//            item-> println("Mapping $item ${Thread.currentThread().name}")
//            return@map item.toInt()
//        }
//        .subscribeOn(Schedulers.computation())
//        .subscribe {
//                item -> println("Received $item${Thread.currentThread().name}")
//        }
//    runBlocking { delay(1000) }

/*======================================= observeOn =============================================*/
    Observable.fromIterable(listOf("1","2","3","4","5"))
        .doOnNext { item -> println("DoOnNext $item - ${Thread.currentThread().name}") }
        .observeOn(Schedulers.computation())//(1)
        .map {
                item->
            println("Mapping $item - ${Thread.currentThread().name}")
            return@map item.toInt()
        }
        .observeOn(Schedulers.io())//(2)
        .subscribe {
                item -> println("Received $item -${Thread.currentThread().name}")
        }
    runBlocking { delay(1000) }

}

// simulate user typing behavior
inline fun createObservable():Observable<String> = Observable.create<String> {
        it.onNext("R")//(4)
        runBlocking { delay(100) }//(5)
        it.onNext("Re")
        it.onNext("Reac")
        runBlocking { delay(130) }
        it.onNext("Reactiv")
        runBlocking { delay(140) }
        it.onNext("Reactive")
        runBlocking { delay(250) }//(6)
        it.onNext("Reactive P")
        runBlocking { delay(130) }
        it.onNext("Reactive Pro")
        runBlocking { delay(100) }
        it.onNext("Reactive Progra")
        runBlocking { delay(100) }
        it.onNext("Reactive Programming")
        runBlocking { delay(300) }
        it.onNext("Reactive Programming in")
        runBlocking { delay(100) }
        it.onNext("Reactive Programming in Ko")
        runBlocking { delay(150) }
        it.onNext("Reactive Programming in Kotlin")
        runBlocking { delay(250) }
        it.onComplete()
    }

data class MyItem3(val id: Int) {
    init {
        print("MyItem Created $id;\n")
    }
}

data class Movie(var name : String)


