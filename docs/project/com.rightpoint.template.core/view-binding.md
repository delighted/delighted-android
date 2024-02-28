//[project](../index.md)/[com.rightpoint.template.core](index.md)/[viewBinding](view-binding.md)



# viewBinding
[androidJvm]
Brief description


A convenience extension function to declare a Lazy inside of an [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html). The binding will not be inflated until it is invoked within the Activity, usually on the [Activity.setContentView](https://developer.android.com/reference/kotlin/android/app/Activity.html#setcontentview) call.


Content
inline fun <[V](view-binding.md) : ViewBinding> [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html).[viewBinding](view-binding.md)(crossinline inflateBinding: ([LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html)) -> [V](view-binding.md)): [Lazy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)<[V](view-binding.md)>


[androidJvm]
Brief description


A convenience extension function to declare a [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md) inside of a Fragment.


Content
fun <[B](view-binding.md) : ViewBinding> [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[viewBinding](view-binding.md)(factory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -> [B](view-binding.md)): [FragmentViewBindingDelegate](-fragment-view-binding-delegate/index.md)<[B](view-binding.md)>
