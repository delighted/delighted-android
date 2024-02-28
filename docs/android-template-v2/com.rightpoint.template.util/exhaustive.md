//[android-template-v2](../../index.md)/[com.rightpoint.template.util](index.md)/[exhaustive](exhaustive.md)

# exhaustive

[jvm]\
val <[T](exhaustive.md)> [T](exhaustive.md).[exhaustive](exhaustive.md): [T](exhaustive.md)

Helper to force a when statement to assert all options are matched in a when statement. By default,
Kotlin doesn't care if all branches are handled in a when statement. However, if you use the when
statement as an expression (with a value) it will force all cases to be handled. This helper is to
make a lightweight way to say you meant to match all of them.<br/><br/> Usage:<br/>

when(sealedObject) {\
is OneType -> //\
is AnotherType -> //\
}.exhaustive<br/>
