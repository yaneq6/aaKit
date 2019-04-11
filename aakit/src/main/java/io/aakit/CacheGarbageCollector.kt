package io.aakit

import io.kache.core.KacheManager
import io.kache.rxjava.observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import org.reactivestreams.Publisher
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CacheGarbageCollector @Inject constructor(
    private val manager: KacheManager,
    private val scheduler: Scheduler
) : (Publisher<Unit>) -> Disposable {

    override fun invoke(publisher: Publisher<Unit>): Disposable = publisher.observable()
        .delay(GARBAGE_COLLECTION_DELAY, TimeUnit.MILLISECONDS)
        .observeOn(scheduler)
        .subscribe { manager.gc(force = true) }

    private companion object {
        const val GARBAGE_COLLECTION_DELAY = 300L
    }
}