package de.peekandpoke.kraft.async

import de.peekandpoke.kraft.components.Component
import de.peekandpoke.kraft.streams.Stream
import de.peekandpoke.kraft.streams.StreamSource
import de.peekandpoke.kraft.utils.launch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.html.FlowContent

fun <T, C> Component<C>.dataLoader(load: () -> Flow<T>): DataLoader<T> = DataLoader(
    component = this,
    options = DataLoader.Options(
        load = load,
    )
)

class DataLoader<T>(
    component: Component<*>,
    val options: Options<T>,
) {
    data class Options<T>(
        val load: () -> Flow<T>,
    )

    class Render<T> {
        internal var loading: FlowContent.() -> Unit = {}
        internal var loaded: FlowContent.(data: T) -> Unit = {}
        internal var error: FlowContent.(error: Throwable) -> Unit = {}

        fun loading(block: FlowContent.() -> Unit) {
            loading = block
        }

        fun loaded(block: FlowContent.(data: T) -> Unit) {
            loaded = block
        }

        fun error(block: FlowContent.(error: Throwable) -> Unit) {
            error = block
        }
    }

    sealed class State<T> {
        class Loading<T> : State<T>()
        class Loaded<T>(val data: T) : State<T>()
        class Error<T>(val error: Throwable) : State<T>()
    }

    private var currentState: State<T> by component.value(State.Loading()) {
        stateStream(it)
    }

    private var stateStream: StreamSource<State<T>> = StreamSource(currentState)

    val state: Stream<State<T>> = stateStream.readonly

    init {
        reload()
    }

    operator fun invoke(flow: FlowContent, block: Render<T>.() -> Unit) {

        val render = Render<T>().apply(block)

        when (val s = currentState) {
            is State.Loading -> render.loading(flow)
            is State.Loaded<T> -> render.loaded(flow, s.data)
            is State.Error -> render.error(flow, s.error)
        }
    }

    fun value(): T? {
        return (currentState as? State.Loaded<T>)?.data
    }

    fun reload() {
        launch {
            options.load()
                .catch { currentState = State.Error(it) }
                .collect { currentState = State.Loaded(it) }
        }
    }
}