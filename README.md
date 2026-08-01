# Core DB Simulator 🗄️

Core DB Simulator is a comprehensive, strictly OOP-driven database engine implemented purely in **Java**. Designed to simulate the internal architecture of relational and document databases, this engine features a robust execution pipeline, modular storage collections, advanced tree-based indexing, file-backed data persistence, and a built-in benchmarking suite.

## 🏗️ System Architecture & Pipeline

The core engine relies heavily on strict abstraction and the **Command Design Pattern** to ensure scalability and a clean codebase:
* **Execution Engine:** The `ExecutionEngine` acts as the central orchestrator, dispatching database operations based on strictly typed enums (`CommandType`).
* **Query Parsing:** Raw input is processed via the `QueryParser`, translating textual user commands into executable context.
* **Presentation Layer:** The `ConsoleOutput` class manages the user interface and formatted terminal rendering for query results.

## 💾 Storage, Collections & Persistence

While the engine operates primarily in-memory for high-speed execution, it guarantees data safety through explicit persistence layers:
* **File Management (Persistence):** The `FileManager` handles file I/O operations, allowing the database of `Student` entities to be fully serialized to a file and reloaded across different sessions without data loss.
* **Modular Collections:** The storage layer is abstracted via a core `Collection` interface. It supports concrete implementations like `ArrayCollection` and `LinkedListCollection`, allowing the engine to swap underlying memory layouts seamlessly.

## 🛠️ Supported Database Commands

Every database operation is completely isolated into its own handler class under the `Engine.Commands` namespace, ensuring the Single Responsibility Principle:
* **CRUD Operations:** `InsertOne`, `DeleteOne`, `FindById`, `FindAll`
* **Aggregations:** `Count`, `Sum`, `Average`
* **Querying & Utils:** `Filter`, `Import`

## 🧱 Advanced Indexing & Data Structures

UniDB implements highly complex data structures from scratch to handle real-world database search complexities via the `IndexManager`:
* **Tree Indexes:** Custom implementations of **AVL Tree** and **Binary Search Tree (BST)** for hierarchical data sorting and optimized range-based search algorithms.
* **Hash & Inverted Indexes:** Includes `HashIndex` for constant-time $O(1)$ key-value lookups, and an `InvertedIndex` explicitly designed for token-based text search and filtering.

## ⚡ Performance & Benchmarking

To ensure the theoretical algorithms perform well in practice, a dedicated Benchmark module (`BenchmarkMain` & `Benchmark2Main`) is integrated. This suite allows for empirical performance comparisons between different storage structures (Array vs. LinkedList) and indexing strategies (Trees vs. Hashes) under heavy read/write loads.
