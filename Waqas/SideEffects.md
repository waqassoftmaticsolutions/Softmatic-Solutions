# Side-effects

A side-effect is a change to the state of the app that happens outside the scope of a composable function. Due to composables' lifecycle and properties such as unpredictable recompositions, executing recompositions of composables in different orders, or recompositions that can be discarded, composables should ideally be side-effect free.
## Why Side-effects?
The purpose of side effects in Jetpack Compose is to allow for the execution of non-UI related operations that change the state of the app outside of a Composable function in a controlled and predictable manner.
Side effects, such as updating a database or making a network call, should be kept separate from the UI rendering logic to improve the performance and maintainability of the code.

## Benefits
  1) Improved Performance: By executing non-UI related operations outside of the Composable functions, the UI rendering logic can remain responsive and performant.
  2) Better Code Organization: By separating non-UI related operations from the UI rendering logic, the codebase becomes easier to understand and maintain.
 ## Functions provided by Jetpack Compose
  1) SideEffect
  2) LaunchedEffect
  3) DisposableEffect
### SideEffect
