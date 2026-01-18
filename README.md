# Clinic Management System

A high-performance system for managing doctors and patients using an efficient dual-index data structure.

## Core Architecture: "Single Node, Dual Existence"

The system solves the problem of indexing the same data by multiple criteria (ID and Workload) without duplication or synchronization overhead. Instead of two trees pointing to the same object, **a single Node object exists in two trees simultaneously** using two sets of pointers:

- **ID Tree (Set A):** Links nodes by Doctor ID for $O(\log n)$ lookups.
- **Workload Tree (Set B):** Links nodes by patient load for $O(\log n)$ range queries.

This ensures atomic updates: changing a value in one "view" is instantly reflected in the other because they are the same memory instance.

## Key Features

- **Augmented 2-3 Trees:** Custom implementation providing $O(\log n)$ insertion, deletion, and search.
- **Range Queries:** The workload tree is augmented with `subtreeValueSum` and `leafCount` to calculate averages and counts in $O(\log n)$.
- **Deterministic Ordering:** Uses composite keys (`LoadKey`) to handle multiple doctors with identical workloads.

## Project Structure

- `ClinicManager.java`: Main API and business logic.
- `Node.java`: The dual-pointer container for Doctor/Patient data.
- `DVNTree.java`: Base balanced tree logic.
  - `DVNTreeS.java`: String-keyed (ID) implementation.
  - `DVNTreeI.java`: Integer-keyed (Workload) implementation with stats augmentation.
- `Doctor.java` / `Patient.java`: Entity models.

## API Usage

```java
ClinicManager clinic = new ClinicManager();
clinic.doctorEnter("Doc1");          // Add doctor
clinic.patientEnter("Doc1", "PatA"); // Assign patient
clinic.numDoctorsWithLoadInRange(0, 5); // O(log n) stats
```
