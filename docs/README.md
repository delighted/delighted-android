# Documentation

## Table of contents

* [KDocs](project/index.md)
* [Code coverage](code_coverage.md)
* [Dependency configuration](dependency_configuration.md)
* [Pull request template](pull_request_template.md)
* [Google Play App Publishing](google_play_configuration.md)

## Releasing a new version to Maven + Github release

* Update version number to the desired new version in [Config.kt](../buildSrc/src/main/java/plugins/Config.kt)
* Run `bundle exec fastlane android createMavenRelease`
* Run `bundle exec fastlane android createGitHubRelease`
