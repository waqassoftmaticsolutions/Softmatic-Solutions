# Compose
Jetpack Compose is a modern toolkit for building native Android UI. It's designed to simplify UI development by using a declarative programming model, where the UI is defined in terms of Composable functions.
## Why Jetpack Compose?
Following are the reasons to choose the Jetpack Compose
  1) Simplified UI Development: Declarative syntax reduces boilerplate and simplifies UI creation.
  2) State Management: Automatically updates UI in response to state changes.
  3) Modern Design Principles: Built-in support for Material Design with customizable theming.
  4) Flexible and Composable UI Components: Composable functions enable reusable and testable UI components.
  5) Performance: Efficient UI rendering optimized for modern devices.
## Key points
  1) **@Composable Annotation:** Functions that define the UI in Compose are annotated with @Composable. This tells the Compose framework that this function should be used to build UI elements.
  2) **Composition:** The process of building the UI tree from Composable functions.
  3) It is not a part of framework it is Jetpack library
  4) The library helps in making UI in the android
  5) It follows declarative approach
  6) Declarative means that you describe what the UI should look like for a given state, It works on **What**
  7) Imperative means to do work step by step. It works on the **How**
  8) Compose based on the Composition ( We add what functionalty or what feature we need in our app easily by just importing the library)
  9) 
## Lifecycle
The life cycle consists of 3 steps:
1) 1st Step -> Enter the Composition
2) 2nd Step -> Recomposition 0 or more times
3) Leave the Composition
### Initial Composition
The first time a composable function is called to build the UI, it is called the initial composition.
#### Example
When an app first launches and the UI is displayed, the initial composition occurs as the composable functions generate the necessary UI components.
### Recomposition
Recomposition is the process where a composable function is called again to update the UI in response to a change in state or data.
#### Example
If a button click increases a counter, and the counter's value is displayed in the UI, changing the counter's value triggers a recomposition to update the displayed value.

# Recomposition
Recomposition is the process where a composable function is called again to update the UI in response to a change in state or data.
### Process 
Only the parts of the UI that depend on the changed state are re-evaluated and updated, making the process efficient. Compose intelligently determines what needs to be updated and avoids unnecessary recompositions.
## Key Points
1) Selective Updates: Recomposition selectively re-runs only the parts of the UI that are affected by state changes, avoiding unnecessary updates to the entire UI.
2) State-Driven: It is triggered by changes in the state or data that the Composable functions depend on.
3) Performance Optimization: Recomposition helps in optimizing performance by updating only what’s necessary, resulting in smoother and more responsive UIs.
## Rules
1) Can execute in any order: it means that the Compose framework does not guarantee the order in which Composable functions are re-executed during a recomposition.
     1) Independence: Composable functions are designed to be independent of each other. The framework may decide to recompose different parts of the UI at different times, depending on which parts of the state have changed.
     2) Concurrency: Recomposition might occur in parallel, especially on different parts of the UI, meaning that one composable might be recomposed before, after, or simultaneously with another.
     3) No Assumptions: You should not assume a specific sequence of recomposition calls, as the framework optimizes the recomposition process to be as efficient as possible, which can lead to non-linear execution.
2) Can Run in parallel: It means that multiple parts of the UI can be recomposed simultaneously on different threads.
3) Don't do any any complex task here in the composable function we must do it in some other background thread
4) It tries to use less part of functions and other functionas
5) It is optimistic and may cancel anytime.

# State Management
State management refers to how an application handles and maintains its data (state) over time, especially as the user interacts with the app and as the app transitions between different screens or lifecycle states. Proper state management ensures that the UI reflects the current state accurately and remains consistent and responsive.
State: Refers to the data or values that determine the behavior and appearance of the UI.
## Types of State Management
1) **Stateful Components**
**Definition:** Components or classes that manage their own state internally.
### Characteristics:
  1) **Internal State:** Maintains its own state within the component.
  2) **Lifecycle:** The state is tied to the lifecycle of the component.
  3) **Example:** A TextField in Jetpack Compose that manages its own text input.
2) **Stateless Components**
**Definition:** Components that do not manage their own state but instead receive state as input (props) from their parent.
### Characteristics:
  1) **No Internal State:** They rely solely on the data passed to them.
  2) **Reusability:** Often more reusable and easier to test because they don't have internal state management.
  3) **Example:** A Text composable in Jetpack Compose that only displays data passed to it.
3) **State Hoisting**
**Definition:** A pattern where state is moved up to a parent composable or component and passed down to child components as parameters.
### Characteristics:
  1) **Centralized State:** The state is managed in a parent component, and changes are propagated down to child components.
  2) **Single Source of Truth:** The parent component is responsible for managing and updating the state, which helps in keeping the state consistent across different child components.
  3) **Example:** Managing the state of a TextField in a parent composable and passing the value and onChange handler to it.
