# Clinic Management System - Advanced Data Structures

This project implements a high-performance **Clinic Management System** designed to handle complex dynamic data operations efficiently. The core innovation of this system is its custom-built **Dual-View Data Structure**, which allows a single data entity (e.g., a Doctor) to coexist in multiple sort orders simultaneously without data duplication or synchronization overhead.

## Key Problem & Solution

### The Challenge
In many real-world systems, we need to index objects by multiple criteria. For a clinic, we need to:
1.  **Search/Access Doctors by ID**: fast lookups (O(log n)) for administrative tasks.
2.  **Query by Workload**: efficiently find doctors with specific patient loads or calculate statistics (e.g., "average load of doctors with 10-20 patients").
3.  **Dynamic Updates**: As patients arrive/leave, a doctor's workload changes. This requires removing them from their old position in the "Workload Tree" and inserting them into a new one, *while keeping their position in the "ID Tree" unchanged*.

### The Solution: "Single Node, Dual Existence"
Instead of maintaining two separate data structures that point to the same object (which risks data inconsistency) or duplicating data (which wastes memory), we implemented a custom **DVNTree (Dynamic Value Node Tree)**.

In this architecture, **a single `Node` object contains two independent sets of pointers**:
-   **Set A (`leftById`, `rightById`, ...)**: Links the node into the `doctorsTree` (sorted by ID).
-   **Set B (`leftByValue`, `rightByValue`, ...)**: Links the same node into the `popularityTree` (sorted by Workload).

#### Architecture Diagram
```text
       [ ID Tree View ]                   [ Workload Tree View ]
              |                                     |
      +-------+-------+                     +-------+-------+
      |  ParentById   |                     | ParentByValue |
      +-------+-------+                     +-------+-------+
              |                                     |
              v                                     v
      +-----------------------------------------------------+
      |                     Node Object                     |
      |-----------------------------------------------------|
      |  Data: Doctor(ID="D123", Load=5)                    |
      |-----------------------------------------------------|
      |  LeftById   |  RightById  |  LeftByValue | Right... |  <-- Two sets of links
      +-----------------------------------------------------+
```

## Technical Highlights

### 1. Custom Balanced Tree Implementation (2-3 Tree Variant)
The system is built on a custom implementation of a balanced search tree (similar to a 2-3 Tree or B-Tree), providing **O(log n)** time complexity for:
-   Insertion (`insert`)
-   Deletion (`delete`)
-   Search (`search`)

### 2. Augmented Tree for Range Queries
The `popularityTree` (`DVNTreeI`) is an **Augmented Data Structure**. Every node maintains:
-   `subtreeValueSum`: The sum of workloads in its subtree.
-   `leafCount`: The number of doctors in its subtree.

This allows us to answer complex range queries in **O(log n)** time, such as:
-   `numDoctorsWithLoadInRange(low, high)`: Count doctors with patient load between `X` and `Y`.
-   `averageLoadWithinRange(low, high)`: Calculate the average workload for a specific subset of doctors.

### 3. Zero-Copy Synchronization
Because the `doctorsTree` and `popularityTree` share the exact same `Node` instances in memory:
-   **Instant Updates**: Modifying a doctor's patient count (`dNode.setValue(...)`) instantly updates the data for both views.
-   **Atomic Consistency**: There is no "lag" between the ID index and the Workload index.
-   **Memory Efficiency**: We avoid the overhead of wrapper objects for every index.


### 4. Tie-Breaking with Composite Keys
To handle multiple doctors having the same workload, the `popularityTree` uses a `LoadKey` (Workload + InsertionTime). This ensures a deterministic total ordering even when values collide.

## Project Structure

-   **`src/ClinicManager.java`**: The main controller. Handles business logic and coordinates the trees.
-   **`src/Node.java`**: The core container. Holds the dual-pointer sets and the payload data (Doctor/Patient).
-   **`src/DVNTree.java`**: Abstract base class for the balanced tree logic.
    -   **`src/DVNTreeS.java`**: Implementation for String keys (ID).
    -   **`src/DVNTreeI.java`**: Implementation for Integer keys (Workload), including the statistical augmentation.
-   **`src/Doctor.java` / `src/Patient.java`**: Data models. Doctors also maintain their own internal queue of patients.

## Usage

The `ClinicManager` class exposes the public API:

```java
ClinicManager clinic = new ClinicManager();

// O(log n) - Doctor enters the system
clinic.doctorEnter("Doc1"); 

// O(log n) - Patient added to Doc1
clinic.patientEnter("Doc1", "PatA"); 

// O(log n) - Get complex stats
// For example
int count = clinic.numDoctorsWithLoadInRange(0, 5); 
```