//[android-template-v2](../../index.md)/[com.rightpoint.template.core](index.md)/[launchWhenViewCreated](launch-when-view-created.md)

# launchWhenViewCreated

[androidJvm]\
fun [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[launchWhenViewCreated](launch-when-view-created.md)(block: suspend CoroutineScope.() -> [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Launches and runs the given block when the [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html) of the Fragment's View [LifecycleCoroutineScope](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleCoroutineScope.html) is at least in [Lifecycle.State.CREATED](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.State.CREATED.html) state.

The returned Job will be cancelled when the [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html) of the View is destroyed.

## See also

androidJvm

| | |
|---|---|
| Lifecycle.whenCreated |  |
| [androidx.lifecycle.LifecycleCoroutineScope](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleCoroutineScope.html) |  |
