//[android-template-v2](../../../index.md)/[com.rightpoint.template.core](../index.md)/[FragmentViewBindingDelegate](index.md)

# FragmentViewBindingDelegate

[androidJvm]\
class [FragmentViewBindingDelegate](index.md)<[V](index.md) : ViewBinding>(**
fragment**: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)
, **
bindingFactory**: ([View](https://developer.android.com/reference/kotlin/android/view/View.html))
-> [V](index.md)) : [AutoClearedValue](../-auto-cleared-value/index.md)<[V](index.md)>

An [AutoClearedValue](../-auto-cleared-value/index.md) that is specifically a ViewBinding inside of
a Fragment.

## Constructors

| | |
|---|---|
| [FragmentViewBindingDelegate](-fragment-view-binding-delegate.md) | [androidJvm]<br>fun <[V](index.md) : ViewBinding> [FragmentViewBindingDelegate](-fragment-view-binding-delegate.md)(fragment: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), bindingFactory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -> [V](index.md)) |

## Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | [androidJvm]<br>open operator override fun [getValue](get-value.md)(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>): [V](index.md) |
| [setValue](index.md#1650892065%2FFunctions%2F1667817856) | [androidJvm]<br>open operator override fun [setValue](index.md#1650892065%2FFunctions%2F1667817856)(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>, value: [V](index.md)) |
