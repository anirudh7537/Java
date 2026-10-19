<<<<<<< 

=======
# MVC Calculator (Java Swing)

A simple GUI-based calculator built in Java Swing, following the **MVC (Model-View-Controller)** architecture. It performs basic arithmetic operations — addition, subtraction, multiplication, and division.

## Architecture

View (Swing GUI) → Controller (ActionListener) → Model (Calculations)


- **Model** (`CalculatorModel.java`) — Contains pure business logic (add, subtract, multiply, divide). No GUI code.
- **View** (`CalculatorView.java`) — Builds the GUI using `JFrame`, `JLabel`, `JTextField`, and `JButton`. Never performs calculations.
- **Controller** (`CalculatorController.java`) — Bridges View and Model. Reads input from View, calls Model methods, and displays the result back in View.
- **Main** (`Main.java`) — Entry point that creates the View and Model, and wires them together via the Controller.

## Features

- Basic arithmetic: `+`, `-`, `*`, `/`
- Clean separation of concerns via MVC
- Division by zero handled gracefully (returns `0` instead of crashing)

## How to Run

1. Clone the repository:
```bash
   git clone https://github.com/anirudh7537/mvc-calculator-java-swing.git
   cd mvc-calculator-java-swing
```

2. Compile:
```bash
   javac Main.java CalculatorView.java CalculatorModel.java CalculatorController.java
```

3. Run:
```bash
   java Main
```

## Demo
| MVC Calculator |
First Number [ 25 ]
Second Number [ 10 ]
[+] [-] [*] [/]
Result [ 35.0 ]

## Tech Stack

- Java
- Java Swing (GUI)
- MVC Design Pattern

## Author

**Anirudh** — [GitHub](https://github.com/anirudh7537)
>>>>>>> 3b84be2 (Add README)
