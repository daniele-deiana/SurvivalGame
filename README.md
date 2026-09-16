"# SurvivalGame" 
# Text-Based Java RPG Engine

Welcome to my very first programming project! As a beginner developer stepping into the world of software development and Object-Oriented Programming (OOP), I built this command-line survival RPG engine to apply theoretical computer science concepts to a practical, interactive project.

I hope you enjoy exploring the codebase and playing the game as much as I enjoyed building it! Feedback, suggestions, and code reviews are always welcome as I continue learning and refining my development skills.

---

### **About the Project**

The application is a terminal-based survival game where players navigate through interactive chapters, manage health and resources, and face randomized survival encounters. The engine balances narrative progression with interactive decision-making, requiring players to heal, explore, or fight based on their current state.

### **Key Features**

* **Turn-Based Mechanics:** Complete control over player actions during each turn, including inventory management, resource usage, and chapter progression.
* **Polymorphic Event System:** Randomly generates dynamic world events—such as hostile encounters (`Conflitto`) or safe havens (`Rifugio`)—leveraging Java's runtime polymorphism.
* **File I/O Management:** Integrated custom file handling (`GestoreFile`) to read story scenarios from external text files and write post-game summaries to disk.
* **Resilient CLI Input:** Input validation using `Scanner` to gracefully intercept invalid tokens (`InputMismatchException`) and prevent infinite loops.
* **Type-Safe Inventory:** Utilizes Java `Enum` types for item tracking to ensure compile-time type safety instead of relying on hardcoded strings.

---

### **Technical Concepts Applied**

Building this initial project helped me gain hands-on experience with core Java fundamentals:

* **Object-Oriented Design:** Inheritance hierarchies, method overriding, encapsulation, and utility class patterns.
* **Java Memory Model:** Understanding Stack vs. Heap allocation, reference types, and the String Constant Pool.
* **Exception Handling:** Managing both Checked (e.g., `InterruptedException`, `IOException`) and Unchecked exceptions.
* **Execution Control:** Using multi-threading (`Thread.sleep`) for visual pacing in the command-line interface.

---
