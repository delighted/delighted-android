//[delighted-android](../../index.md)/[com.delighted.sampleapp.core](index.md)/[viewBinding](view-binding.md)

# viewBinding

[androidJvm]\
inline fun &lt;[V](view-binding.md) : ViewBinding&gt; [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html).[viewBinding](view-binding.md)(crossinline inflateBinding: ([LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html)) -&gt; [V](view-binding.md)): [Lazy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)&lt;[V](view-binding.md)&gt;

A convenience extension function to declare a Lazy inside of an [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html). The binding will not be inflated until it is invoked within the Activity, usually on the [Activity.setContentView](https://developer.android.com/reference/kotlin/android/app/Activity.html#setcontentview) call.

[androidJvm]\
fun &lt;[B](view-binding.md) : ViewBinding&gt; [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[viewBinding](view-binding.md)(factory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -&gt; [B](view-binding.md)): [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md)&lt;[B](view-binding.md)&gt;

A convenience extension function to declare a [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md) inside of a Fragment.
