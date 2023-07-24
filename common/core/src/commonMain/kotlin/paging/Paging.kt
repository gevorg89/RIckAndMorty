package paging

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class Paging<T> {

    private var limit = 20L
    private var page: Int = 1
    private val offset: Long
        get() = (page - 1) * limit

    private var useLocal = false

    abstract suspend fun checkForFetch(): Boolean
    abstract suspend fun getLocalCount(): Long

    abstract suspend fun getLocal(limit: Long, offset: Long): List<T>
    abstract suspend fun fetch(page: Int)

    abstract suspend fun invalidateRepository()

    private val _items: MutableStateFlow<List<T>> = MutableStateFlow(emptyList())
    val items = _items.asStateFlow()

    suspend fun checkLoad() {
        if (page == 1) {
            if (checkForFetch()) {
                fetch(page)
            }
        } else {
            useLocal = getLocalCount() > offset
            if (!useLocal) {
                fetch(page)
            }
        }
        _items.update { getLocal(limit, offset) }
        page++
    }

    open suspend fun invalidate() {
        page = 1
    }
}