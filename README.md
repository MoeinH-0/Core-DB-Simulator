# Core DB Simulator 🗄️

A custom-built, document-based NoSQL database engine simulator (inspired by MongoDB) developed entirely in **Java**. This project is a deep dive into core computer science concepts, built from scratch using custom data structures to demonstrate how structural choices directly impact database performance and query optimization.

## 🧱 Custom Data Structures & Storage

Instead of relying on standard library collections, the core of this engine is powered by diverse, manually implemented data structures to handle real-world search and storage complexities:
*   **Storage Layer:** Abstracted via a core `Collection` interface, with concrete implementations for both **Dynamic Arrays** (`ArrayCollection`) and **Doubly Linked Lists** (`LinkedListCollection`) to compare mutation performance.
*   **Tree Indexes:** Custom implementations of **AVL Tree** (with auto-balancing rotations for $O(\log n)$ operations) and standard **Binary Search Tree (BST)**.
*   **Hash & Inverted Indexes:** Includes a custom `HashIndex` utilizing collision chaining for $O(1)$ lookups, and an `InvertedIndex` explicitly designed for full-text token searches.

## 🏗️ Strict 3-Layer Architecture

To maintain modularity and avoid spaghetti code, the system enforces a strict top-down three-layer architecture:
*   **1. Query Parser:** Tokenizes raw string inputs, detects command types, and extracts parameters to generate executable `Command` objects.
*   **2. Execution Engine & Optimizer:** The core orchestrator. It manages transaction rollbacks via a Stack, sequential batch processing via a Queue, and features a **Smart Query Optimizer** that intercepts queries to utilize indexes before falling back to linear scans.
*   **3. Storage Engine:** Manages in-memory data operations completely independent of the query logic, seamlessly interacting with the `FileManager` for robust data serialization and persistence.

## 🛠️ Supported Database Commands

The architecture utilizes the **Command Design Pattern** to isolate database operations, enforcing the Single Responsibility Principle:
*   **CRUD:** `InsertOne`, `DeleteOne`, `FindById`, `FindAll`
*   **Aggregations:** `Count`, `Sum`, `Average`
*   **Utilities:** `Filter`, `Import` (supports bulk loading from CSV files)

## ⚡ Benchmarking & Big O Analysis

A dedicated benchmarking suite is integrated to empirically test theoretical time complexities under heavy read/write loads:
*   Compares the degradation of standard BSTs against balanced AVL Trees during sequential data inserts.
*   Graphs performance metrics of array vs. linked list mutations.
*   Measures execution times across Full Scans, BST Indexes, and Hash Indexes for massive datasets.
