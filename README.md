# ✈️ Air Traffic Simulation

A Java-based desktop application for simulating air traffic, developed using Object-Oriented Programming principles and a graphical user interface (GUI).

This project was created as part of the Object Oriented Programming 2 course at the School of Electrical Engineering, University of Belgrade.

---

## 🎯 Project Goal

The goal of this project is to design and implement an air traffic simulation system using Java, with a strong emphasis on:

- Object-Oriented Programming (OOP)
- Clean and modular architecture
- Separation of concerns
- Maintainability and extensibility

The application enables users to manage airports and flights through an intuitive GUI, while ensuring robust error handling and clear system feedback.

---

## 🧱 Architecture & Design

A key requirement of this project is strict adherence to layered architecture and OOP principles.

The system is organized into clearly separated components:

- **Model** → data structures (airports, flights)
- **Logic / Manager** → business logic and system behavior
- **UI (Form / Map / Table)** → graphical interface and user interaction
- **Utilities** → file handling, parsing, timers, and helper functions

Each layer has a well-defined responsibility with minimal coupling between components, ensuring the system is easy to understand, maintain, and extend.

---

## ⚠️ Error Handling (Core Requirement)

Robust error handling is a critical part of the system.

All potential errors must be:

- properly detected using exception handling
- clearly communicated to the user
- accompanied by helpful guidance

### Covered scenarios:

- Invalid user input (coordinates, time format, duplicates)
- File handling errors (missing file, invalid format, parsing issues)
- Runtime issues during simulation

### Example message:
```
File does not contain expected columns. Please check the format.
```

👉 The goal is that the user always understands:
- what went wrong
- why it happened
- how to fix it  
without needing to restart the application.

---

## 🧩 Project Phases

### 🅰️ Phase A – Data Input & Management

- Input of airports:
  - Name
  - Unique 3-letter code (uppercase)
  - Coordinates (x, y) in range [-90, 90]

- Input of flights:
  - Departure & destination
  - Departure time
  - Duration

- Features:
  - Tabular data display
  - CSV import/export
  - Input validation with detailed feedback

### ⏱️ Auto Shutdown

- App closes after **60 seconds of inactivity**
- Warning dialog appears in last **5 seconds**
- User can cancel shutdown

---

### 🅱️ Phase B – Map Visualization

- Airports displayed as **gray squares**
- Positioned using coordinates
- Each shows its 3-letter code

#### Interaction:
- Click → select airport → blinking red
- Click again → deselect

#### Additional features:
- Timer pauses when airport is selected
- Airport filtering via checkbox list

---

### 🅲 Phase C

(Planned extension / further improvements)

---

## 🛠️ Technologies

- Java
- Swing / JavaFX
- OOP (encapsulation, inheritance, polymorphism)

---

## 🚀 How to Run

```bash
git clone https://github.com/Paycee/Flight-Simulator.git
```

Open project in IDE (IntelliJ / Eclipse) and run the main class.

---

## 📄 Notes

- Coordinates use 2D Cartesian system
- Airport codes must be unique
- Input validation is strictly enforced
- System is designed for clarity, robustness, and user-friendly interaction

---

## 📚 License

This project is for educational purposes.# ✈️ Air Traffic Simulation

A Java-based desktop application for simulating air traffic, developed using Object-Oriented Programming principles and a graphical user interface (GUI).

This project was created as part of the Object Oriented Programming 2 course at the School of Electrical Engineering, University of Belgrade.

---

## 🎯 Project Goal

The goal of this project is to design and implement an air traffic simulation system using Java, with a strong focus on:

- Object-Oriented Programming (OOP) concepts
- Clean and modular architecture
- Separation of concerns (model, logic, UI)
- Maintainable and extensible code

The application provides an intuitive graphical interface through which users can manage airports and flights, while receiving clear feedback in case of errors or invalid input.

---

## 🧩 Project Structure

The project is divided into three development phases:

- **Phase A** – Data input and management  
- **Phase B** – Airport map visualization  
- **Phase C** – (future extension)

---

## 🅰️ Phase A – Data Input & Management

This phase implements the core functionality of the system.

### ✈️ Airports

Users can input:
- Airport name
- Unique 3-letter code (uppercase only)
- Coordinates (x, y) in range [-90, 90]

### 🛫 Flights

Users can define:
- Departure and destination airports
- Departure time (hours and minutes)
- Flight duration (in minutes)

### 📊 Features

- Tabular display of all entered data
- CSV file import/export
- Input validation with detailed error messages
- Error handling for:
  - Invalid file format
  - Missing files
  - Incorrect data

### ⏱️ Auto Shutdown

- Application closes after **60 seconds of inactivity**
- Warning dialog appears in the last **5 seconds**
- User can choose to continue working

---

## 🅱️ Phase B – Map Visualization

This phase introduces graphical visualization of airports.

### 🗺️ Map Display

- Airports are shown as **gray squares**
- Positioned based on their coordinates
- Each airport displays its 3-letter code

### 🖱️ Interaction

- Clicking an airport:
  - Selects it
  - Makes it **blink red**
- Clicking again:
  - Deselects it

### ⏸️ Activity Timer

- Timer pauses while an airport is selected

### 🔍 Filtering

- Airports are listed with:
  - Name
  - Code
  - Coordinates
  - Checkbox for visibility

- Users can control which airports are displayed on the map

---

## ⚠️ Error Handling

The application provides clear and user-friendly error messages for:

- Invalid input data
- Duplicate airport codes
- Incorrect time formats
- File reading/parsing issues

Example:
```
File does not contain expected columns. Please check the format.
```

---

## 🛠️ Technologies Used

- Java
- Swing / JavaFX (depending on implementation)
- OOP principles (encapsulation, inheritance, polymorphism)

---

## 🚀 How to Run

1. Clone the repository:
```bash
git clone https://github.com/Paycee/CLI.git
```

2. Open the project in your IDE (IntelliJ / Eclipse)

3. Run the main class

---

## 📄 Notes

- Coordinates are based on a 2D Cartesian system
- All airport codes must be unique
- Input validation is strictly enforced

---

## 📚 License

This project is for educational purposes.
