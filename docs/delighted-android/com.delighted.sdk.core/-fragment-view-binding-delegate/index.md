//[delighted-android](../../../index.md)/[com.delighted.sdk.core](../index.md)/[FragmentViewBindingDelegate](index.md)

# FragmentViewBindingDelegate

[androidJvm]\
class [FragmentViewBindingDelegate](index.md)&lt;[V](index.md) : ViewBinding&gt;(fragment: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), bindingFactory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -&gt; [V](index.md)) : [AutoClearedValue](../-auto-cleared-value/index.md)&lt;[V](index.md)&gt;

An [AutoClearedValue](../-auto-cleared-value/index.md) that is specifically a ViewBinding inside of a Fragment.

## Constructors

| | |
|---|---|
| [FragmentViewBindingDelegate](-fragment-view-binding-delegate.md) | [androidJvm]<br>fun &lt;[V](index.md) : ViewBinding&gt; [FragmentViewBindingDelegate](-fragment-view-binding-delegate.md)(fragment: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), bindingFactory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -&gt; [V](index.md)) |

## Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | [androidJvm]<br>open operator override fun [getValue](get-value.md)(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)&lt;*&gt;): [V](index.md) |
| [setValue](index.md#-1658163787%2FFunctions%2F-1909672370) | [androidJvm]<br>open operator override fun [setValue](index.md#-1658163787%2FFunctions%2F-1909672370)(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)&lt;*&gt;, value: [V](index.md)) |
