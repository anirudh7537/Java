# MVC Calculator

## Overview

This project is a calculator organized around the
**Model--View--Controller (MVC)** design pattern. MVC separates
application data and logic from the user interface and the code that
coordinates user actions.

The source files include `CalculatorModel.java`, `CalculatorView.java`,
`CalculatorController.java`, and `Main.java`.

## Objectives

-   Understand the Model--View--Controller pattern.
-   Separate calculation logic from user-interface code.
-   Learn how a controller receives input and coordinates model/view
    behavior.
-   Make a small application easier to maintain and extend.

## Technologies

-   Java / JDK
-   MVC design pattern
-   The exact UI toolkit should be confirmed in `CalculatorView.java`
    (for example, Swing if it imports `javax.swing`).

## Project structure

``` text
MVCCalculator/
├── CalculatorModel.java
├── CalculatorView.java
├── CalculatorController.java
├── Main.java
├── .gitignore
└── README.md
```

## Responsibilities of each component

-   **Model --- `CalculatorModel.java`:** contains the calculation state
    and/or arithmetic operations.
-   **View --- `CalculatorView.java`:** displays the calculator
    interface and exposes user interactions.
-   **Controller --- `CalculatorController.java`:** connects view
    actions to model operations and updates the view.
-   **Entry point --- `Main.java`:** starts the application and wires
    the components together.

Confirm the precise methods and supported operations by inspecting each
source file.

## Prerequisites

-   Install a JDK compatible with the source.
-   Open a terminal in the `MVCCalculator` directory.

## Compile and run

If the files use the default package and have no external dependencies:

``` powershell
javac *.java
java Main
```

If the files declare a package, compile from the source root and run
`Main` using its fully qualified class name. You can also open the
folder in an IDE and run `Main.java`.

## MVC flow

A typical MVC interaction follows this sequence:

1.  The user performs an action in the view.
2.  The controller receives the action.
3.  The controller asks the model to perform the calculation.
4.  The model returns the result.
5.  The controller tells the view to display the result.

## Testing checklist

-   Try each operation implemented by the calculator.
-   Test zero and negative values where supported.
-   Check how division by zero is handled if division is implemented.
-   Check invalid or empty input handling.
-   Confirm that calculation logic remains separate from UI
    event-handling code.

## Future improvements

-   Add validation and friendly error messages.
-   Add unit tests for the model.
-   Add keyboard support and a clearer layout.
-   Keep business logic independent from the UI toolkit.
-   Document the exact operations supported by the current
    implementation.

## Learning outcome

The project demonstrates how MVC can organize a small Java application
into components with distinct responsibilities.
