# jlox - A Tree-Walk Interpreter for Lox 🦎

**jlox** is a Java-based interpreter for the Lox programming language, developed by following **Part I** of the book [*Crafting Interpreters*](https://craftinginterpreters.com/) by Robert Nystrom.

This interpreter uses a **tree-walk** evaluation approach and demonstrates how to build a programming language from the ground up—starting with scanning, parsing, static resolution, and interpreting.

---

## 📖 About Lox

**Lox** is a simple, dynamically-typed, object-oriented language designed for learning language implementation. It features:

- JavaScript-like syntax
- Lexical scoping
- First-class functions and closures
- Classes and inheritance
- Dynamic typing
- Garbage collection (via the JVM in jlox)

---

## 📁 Project Structure

jlox/
├── com/
│ └── craftinginterpreters/
│ └── lox/
│ ├── Expr.java
│ ├── Stmt.java
│ ├── Token.java
│ ├── TokenType.java
│ ├── Scanner.java
│ ├── Parser.java
│ ├── Interpreter.java
│ ├── Resolver.java
│ ├── Environment.java
│ ├── Lox.java
│ └── ... (other classes)
└── README.md

yaml
Copy
Edit

---

## 🛠️ Getting Started

### ✅ Prerequisites

- Java Development Kit (JDK) 8 or later
- Terminal or command prompt
- A code editor (e.g., VS Code, IntelliJ, etc.)

### 📥 Clone the Repository

```bash
git clone https://github.com/your-username/jlox.git
cd jlox
🧾 Compile the Source
bash
Copy
Edit
javac com/craftinginterpreters/lox/*.java
▶️ Run the Interpreter
Run the REPL (Interactive Mode)
bash
Copy
Edit
java com.craftinginterpreters.lox.Lox
Run a .lox Script
bash
Copy
Edit
java com.craftinginterpreters.lox.Lox path/to/script.lox
💡 Example Usage
Example Lox Script
lox
Copy
Edit
print "Hello, world!";

var a = 10;
var b = 20;

fun add(x, y) {
  return x + y;
}

print add(a, b); // Outputs: 30
Save this as example.lox, then run:

bash
Copy
Edit
java com.craftinginterpreters.lox.Lox example.lox
📚 Learning Resources
This interpreter is built by following:

📘 Crafting Interpreters
Robert Nystrom's book on building a language and virtual machine from scratch:
https://craftinginterpreters.com

Additional links:

Book GitHub repo

Lox Language Guide

🧪 Testing
Lox does not come with a formal testing framework in the book, but you can manually test features using .lox files and REPL examples.

You can also write small unit tests for your classes using a framework like JUnit, or extend the interpreter to include automated testing features.

📜 License
This project is provided for educational purposes, based on the structure outlined in the book Crafting Interpreters. Unless otherwise stated, this repository is under the MIT License.

🤝 Contributing
This repository is primarily a personal or educational implementation of jlox as described in the book. Contributions are welcome if you are:

Extending the language

Fixing bugs

Improving tooling

Adding documentation

To contribute:
