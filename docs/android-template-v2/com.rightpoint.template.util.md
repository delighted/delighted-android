//[android-template-v2](index.md)/[com.rightpoint.template.util](com.rightpoint.template.util.md)



# Package com.rightpoint.template.util


## Properties

|  Name|  Summary|
|---|---|
| [exhaustive](com.rightpoint.template.util.md#com.rightpoint.template.util//exhaustive/TypeParam(bounds=[kotlin.Any?])#/PointingToDeclaration/)|  [jvm] <br><br><br><br>Helper to force a when statement to assert all options are matched in a when statement. By default, Kotlin doesn't care if all branches are handled in a when statement. However, if you use the when statement as an expression (with a value) it will force all cases to be handled. This helper is to make a lightweight way to say you meant to match all of them.<br/> <br/> Usage:<br/><br><br>when(sealedObject) {  <br>    is OneType -> //  <br>    is AnotherType -> //  <br>}.exhaustive<br><br><br/><br><br><br><br>val <[T](com.rightpoint.template.util.md#com.rightpoint.template.util//exhaustive/TypeParam(bounds=[kotlin.Any?])#/PointingToDeclaration/)> [T](com.rightpoint.template.util.md#com.rightpoint.template.util//exhaustive/TypeParam(bounds=[kotlin.Any?])#/PointingToDeclaration/).[exhaustive](com.rightpoint.template.util.md#com.rightpoint.template.util//exhaustive/TypeParam(bounds=[kotlin.Any?])#/PointingToDeclaration/): [T](com.rightpoint.template.util.md#com.rightpoint.template.util//exhaustive/TypeParam(bounds=[kotlin.Any?])#/PointingToDeclaration/)   <br>
