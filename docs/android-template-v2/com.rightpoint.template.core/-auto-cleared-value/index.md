//[android-template-v2](../../../index.md)/[com.rightpoint.template.core](../index.md)/[AutoClearedValue](index.md)

# AutoClearedValue

[androidJvm]\
open
class [AutoClearedValue](index.md)<[T](index.md) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)>(**fragment**: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)) : [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)<[Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)
, [T](index.md)>

A lazy property that gets cleaned up when the Fragment's View is destroyed.

Accessing this variable while the Fragment's View is destroyed will throw IllegalStateException.

## Constructors

| | |
|---|---|
| [AutoClearedValue](-auto-cleared-value.md) | [androidJvm]<br>fun [AutoClearedValue](-auto-cleared-value.md)(fragment: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html)) |

## Functions

| Name | Summary |
|---|---|
| [getValue](get-value.md) | [androidJvm]<br>open operator override fun [getValue](get-value.md)(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>): [T](index.md) |
| [setValue](set-value.md) | [androidJvm]<br>open operator override fun [setValue](set-value.md)(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>, value: [T](index.md)) |

## Inheritors

| Name |
|---|
| [FragmentViewBindingDelegate](../-fragment-view-binding-delegate/index.md) |
