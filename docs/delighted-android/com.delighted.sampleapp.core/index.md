//[delighted-android](../../index.md)/[com.delighted.sampleapp.core](index.md)

# Package com.delighted.sampleapp.core

## Types

| Name | Summary |
|---|---|
| [AutoClearedValue](-auto-cleared-value/index.md) | [androidJvm]<br>open class [AutoClearedValue](-auto-cleared-value/index.md)&lt;[T](-auto-cleared-value/index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt;(fragment: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)) : [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)&lt;[Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), [T](-auto-cleared-value/index.md)&gt; <br>A lazy property that gets cleaned up when the Fragment's View is destroyed. |
| [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md) | [androidJvm]<br>class [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md)&lt;[V](-fragment-view-binding-delegate/index.md) : ViewBinding&gt;(fragment: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), bindingFactory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -&gt; [V](-fragment-view-binding-delegate/index.md)) : [AutoClearedValue](-auto-cleared-value/index.md)&lt;[V](-fragment-view-binding-delegate/index.md)&gt; <br>An [AutoClearedValue](-auto-cleared-value/index.md) that is specifically a ViewBinding inside of a Fragment. |

## Functions

| Name | Summary |
|---|---|
| [autoCleared](auto-cleared.md) | [androidJvm]<br>fun &lt;[T](auto-cleared.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[autoCleared](auto-cleared.md)(): [AutoClearedValue](-auto-cleared-value/index.md)&lt;[T](auto-cleared.md)&gt;<br>Creates an [AutoClearedValue](-auto-cleared-value/index.md) associated with this fragment. |
| [viewBinding](view-binding.md) | [androidJvm]<br>inline fun &lt;[V](view-binding.md) : ViewBinding&gt; [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html).[viewBinding](view-binding.md)(crossinline inflateBinding: ([LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html)) -&gt; [V](view-binding.md)): [Lazy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)&lt;[V](view-binding.md)&gt;<br>A convenience extension function to declare a Lazy inside of an [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html). The binding will not be inflated until it is invoked within the Activity, usually on the [Activity.setContentView](https://developer.android.com/reference/kotlin/android/app/Activity.html#setcontentview) call.<br>[androidJvm]<br>fun &lt;[B](view-binding.md) : ViewBinding&gt; [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[viewBinding](view-binding.md)(factory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -&gt; [B](view-binding.md)): [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md)&lt;[B](view-binding.md)&gt;<br>A convenience extension function to declare a [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md) inside of a Fragment. |