# Parking Lot Management System 🚗🅿️

A **Java-based Parking Lot Management System** designed using **core Object-Oriented Programming (OOP) principles** and the **Java Collections Framework**.  
This project focuses on clean design, modularity, and real-world edge cases, making it suitable for **product-based company interviews**.

---

## 📌 Features

- Supports multiple vehicle types:
  - Car
  - Bike
  - Truck
- Vehicle-type-based slot allocation
- Generates parking tickets with entry time
- Calculates parking fees based on duration and vehicle type
- Prevents duplicate vehicle parking
- Handles parking lot full scenarios
- Custom exception handling for invalid operations

---

## 🛠 Tech Stack

- **Language:** Java  
- **Concepts Used:**
  - Object-Oriented Programming (OOP)
    - Abstraction
    - Inheritance
    - Polymorphism
    - Encapsulation
  - Java Collections Framework
  - Custom Exception Handling

---

## 🧠 Design Overview

- **Vehicle** (abstract class)  
  - Implemented by `Car`, `Bike`, `Truck`
- **ParkingLot**
  - Manages parking slots and vehicle allocation
- **Slot**
  - Represents individual parking slots
- **Ticket**
  - Stores vehicle details and entry time
- **ParkingFeeStrategy** (interface)
  - Strategy pattern used for fee calculation
- **Custom Exceptions**
  - `ParkingFullException`
  - `InvalidVehicleException`
  - `ParkingException`

The system is designed to be **easily extensible** — new vehicle types or pricing rules can be added with minimal changes.

---

## 📂 Project Structure

src/
├── model/
│ ├── Vehicle.java
│ ├── Car.java
│ ├── Bike.java
│ ├── Truck.java
│ ├── Slot.java
│ └── Ticket.java
│
├── service/
│ └── ParkingLot.java
│
├── strategy/
│ ├── ParkingFeeStrategy.java
│ └── DefaultFeeStrategy.java
│
├── exception/
│ ├── ParkingException.java
│ ├── ParkingFullException.java
│ └── InvalidVehicleException.java
│
└── Main.java


## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yash-2999/Parking-Lot-Management-System.git
