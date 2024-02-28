//[project](../index.md)/[com.rightpoint.template.core](index.md)/[launchWhenViewCreated](launch-when-view-created.md)



# launchWhenViewCreated
[androidJvm]
Brief description




Launches and runs the given block when the [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html) of the Fragment's View [LifecycleCoroutineScope](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleCoroutineScope.html) is at least in [Lifecycle.State.CREATED](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.State.CREATED.html) state.



The returned Job will be cancelled when the [Lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/Lifecycle.html) of the View is destroyed.





## See also

androidJvm

|  Name|  Summary|
|---|---|
| Lifecycle.whenCreated| <br><br><br><br>
| [LifecycleCoroutineScope](https://developer.android.com/reference/kotlin/androidx/lifecycle/LifecycleCoroutineScope.html)| <br><br><br><br>


Content
fun [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[launchWhenViewCreated](launch-when-view-created.md)(block: [SuspendFunction1](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.coroutines/-suspend-function1/index.html)<CoroutineScope, [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)>)
