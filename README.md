# GitHub Profile Repository using GraphQL API

Project need to use a valid github token to run the app successfully. It is defined in the app level build.gradle file.
(buildConfigField "String", "ACCESS_TOKEN", '"Bearer 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"')
The provided token expire when create a commit to github. So please replace it with the below token when you try to run the app successfully.
Use this token: "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"

## Below requirements are implemented
- Language: Kotlin.
- Frameworks: RxJava, Dagger 2, Data binding, View binding
- Architecture: MVP
- The project run in the latest version of Android Studio.
- Cover business logic layer (Presenter) by unit tests.
- UI test and unit testing implemented.
- Cache data for 1 day and pull to refresh. (This part is implemented but not working properly)

For cache handle using intercepters (for okhttp client) it only support GET http method. Project use POST http method to parse the graphql query as the body parameter.

