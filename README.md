# ✈️ Air Traffic Simulation

![Java](https://img.shields.io/badge/Java-17-blue)
![OOP](https://img.shields.io/badge/Paradigm-OOP-green)
![GUI](https://img.shields.io/badge/UI-Swing%20%2F%20JavaFX-orange)
![Status](https://img.shields.io/badge/Status-Student%20Project-lightgrey)

A Java-based desktop application for simulating air traffic, built with a strong focus on Object-Oriented Programming and clean architecture.

Developed as part of the Object Oriented Programming 2 course at the School of Electrical Engineering, University of Belgrade.

---

## 🎯 Project Goal

The goal of this project is to design a modular and extensible air traffic simulation system with:

- Clear separation of concerns
- Strong OOP design
- Robust error handling
- Intuitive graphical interface

---

## 🧱 Architecture Overview

The system follows a layered, modular design:

```text
+---------------------+
|        UI           |  → Forms, Map, Tables
+---------------------+
|   Logic / Manager   |  → Command handling, simulation logic
+---------------------+
|       Model         |  → Airport, Flight entities
+---------------------+
|      Utilities      |  → File I/O, parsing, timers
+---------------------+
```

### 🔑 Key Principles

- Low coupling between components  
- High cohesion within modules  
- Clear responsibility separation  
- Scalable and maintainable structure  

---

## ⚠️ Error Handling (Critical Requirement)

The system is designed to handle all errors gracefully.

✔ Covers:
- Invalid user input  
- Duplicate airport codes  
- Incorrect time formats  
- File read/write errors  
- Parsing issues  

✔ All errors:
- Are caught using exception handling  
- Provide meaningful messages  
- Guide the user toward a solution  

### Example:
```
File does not contain expected columns. Please check the format.
```

👉 The application never leaves the user confused or stuck.

---

## 🧩 Project Phases

### 🅰️ Phase A – Data Input & Management

- Airport input:
  - Name
  - Unique 3-letter code
  - Coordinates [-90, 90]

- Flight input:
  - Departure & destination
  - Time and duration

- Features:
  - Table view
  - CSV import/export
  - Input validation

### ⏱️ Auto Shutdown

- 60s inactivity → app closes  
- Last 5s → warning dialog  
- User can cancel shutdown  

---

### 🅱️ Phase B – Map Visualization

- Airports shown as **gray squares**
- Positioned by coordinates
- Labeled with airport codes

#### Interaction:
- Click → select → blinking red  
- Click again → deselect  

#### Additional:
- Timer pauses when selected  
- Filtering via checkbox list  

---

### 🅲 Phase C

Future expansion of simulation features.

---

## 🗂️ Project Structure

```text
Flight-Simulator/
 ├── model/
 ├── manager/
 ├── form/
 ├── map/
 ├── table/
 ├── timer/
 ├── MainWindow.java
 ├── main.java
```

---

## 🛠️ Technologies

- Java
- Swing / JavaFX
- OOP (Encapsulation, Inheritance, Polymorphism)

---

## 🚀 How to Run

```bash
git clone https://github.com/Paycee/Flight-Simulator.git
```

Open in IntelliJ / Eclipse and run `main.java`.

---

## 📄 Notes

- Cartesian coordinate system used
- Strict validation rules
- Focus on usability and clarity

---

## 📚 License

Educational project.
