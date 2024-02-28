package plugins

object Config {
    const val major = 1
    const val minor = 0
    const val patch = 0

    const val minSdk = 24
    const val targetSdk = 31
    const val compileSdk = 32
    const val buildTools = "30.0.3"
    val code = (major * 1_000_000) + (minor * 10_000) + (patch * 100)
    val name = "$major.$minor.$patch"
}
