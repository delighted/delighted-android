//[project](../../index.md)/[com.rightpoint.template.core](../index.md)/[FragmentViewBindingDelegate](index.md)



# FragmentViewBindingDelegate
[androidJvm]

An [AutoClearedValue](../-auto-cleared-value/index.md) that is specifically a ViewBinding inside of a Fragment.

class [FragmentViewBindingDelegate](index.md)<[V](index.md) : ViewBinding>(**fragment**: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), **bindingFactory**: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -> [V](index.md)) : [AutoClearedValue](../-auto-cleared-value/index.md)<[V](index.md)>


## Constructors

|  Name|  Summary|
|---|---|
| [FragmentViewBindingDelegate](-fragment-view-binding-delegate.md)|  [androidJvm] fun <[V](index.md) : ViewBinding> [FragmentViewBindingDelegate](-fragment-view-binding-delegate.md)(fragment: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), bindingFactory: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -> [V](index.md))   <br>


## Functions

|  Name|  Summary|
|---|---|
| [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)| [androidJvm]  <br>Content  <br>open operator override fun [equals](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/equals.html)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)  <br><br><br>
| [getValue](get-value.md)| [androidJvm]  <br>Content  <br>open operator override fun [getValue](get-value.md)(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>): [V](index.md)  <br><br><br>
| [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)| [androidJvm]  <br>Content  <br>open override fun [hashCode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/hash-code.html)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)  <br><br><br>
| setValue| [androidJvm]  <br>Content  <br>open operator override fun setValue(thisRef: [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), property: [KProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-property/index.html)<*>, value: [V](index.md))  <br><br><br>
| [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)| [androidJvm]  <br>Content  <br>open override fun [toString](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/to-string.html)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)  <br><br><br>
