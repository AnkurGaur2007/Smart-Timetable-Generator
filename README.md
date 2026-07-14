# Smart Timetable Generator

A Java console application that generates clash-free university timetables using the Backtracking algorithm. The project demonstrates object-oriented design, recursion, and constraint-based scheduling in a simple, beginner-friendly implementation.

# Smart Timetable Generator

Smart Timetable Generator is a console-based Java project that creates a university timetable by placing subjects into valid room and time-slot combinations.

I built this project to practice:

- Object-Oriented Programming
- recursion
- Backtracking
- constraint checking
- clean class design

The idea is simple: given a set of subjects, rooms, and time slots, the program tries to build a timetable without clashes.

This project is meant to be easy to read and understand, especially for a second-year Computer Science student who is learning DSA and Java design basics.

---

## Features

- Clash-free timetable generation
- Recursive Backtracking algorithm
- Faculty conflict detection
- Room conflict detection
- Lab and classroom validation
- Automatic timetable printing
- Console-based interface
- Randomized time-slot ordering for better timetable distribution
- Multiple timetable generation
- Simple statistics after generation

---

## Technologies Used

- Java 17
- Object-Oriented Programming
- Backtracking
- Java Collections
- Console Application

---

## Project Structure

```text
Smart Timetable Generator Project/
├── README.md
└── src/
    ├── Subject.java
    ├── Room.java
    ├── Day.java
    ├── TimeSlot.java
    ├── Lecture.java
    ├── Timetable.java
    ├── ConstraintChecker.java
    ├── TimeSlotGenerator.java
    ├── InputManager.java
    ├── ConsoleHelper.java
    ├── TimetablePrinter.java
    ├── Scheduler.java
    └── Main.java
```

### What each class does

- `Subject.java`
  - stores subject name, faculty name, lectures per week, and lab status
- `Room.java`
  - stores room name, capacity, and whether the room is a lab
- `Day.java`
  - enum for Monday to Friday
- `TimeSlot.java`
  - stores one day and one slot number
- `Lecture.java`
  - combines one subject, one room, and one time slot
- `Timetable.java`
  - stores all scheduled lectures
- `ConstraintChecker.java`
  - checks whether a lecture can be placed
- `TimeSlotGenerator.java`
  - creates all possible teaching slots and can shuffle them
- `InputManager.java`
  - reads user input and creates default rooms
- `ConsoleHelper.java`
  - prints reusable titles, separators, and section lines
- `TimetablePrinter.java`
  - prints the final timetable in a readable format
- `Scheduler.java`
  - uses recursive backtracking to generate the timetable
- `Main.java`
  - starts the program and connects all parts together

---

## How the Algorithm Works

The program uses Backtracking to try different lecture placements until it finds a clash-free timetable.

Here is the process in simple terms:

1. The user enters subjects and their lecture counts.
2. The program converts each subject into repeated lecture entries.
3. The program creates all possible valid time slots.
4. The program tries to place one lecture at a time.
5. For each lecture, it tries every room.
6. For each room, it tries every time slot.
7. Before placing a lecture, it checks all constraints.
8. If the placement is valid, the lecture is added to the timetable.
9. The program then moves to the next lecture using recursion.
10. If a later lecture cannot be placed, the program removes the previous lecture.
11. It then tries the next possible room or time slot.
12. This continues until all lectures are scheduled or no valid solution exists.

In short:

- try
- check
- place
- recurse
- backtrack if needed

### Why backtracking is useful here

Backtracking works well because timetable generation has many possible combinations.

Some placements look fine at first, but later they may block other lectures.

Backtracking helps the program recover from bad choices and try other options.

---

## Time Complexity

Backtracking has exponential worst-case complexity because the program may need to try many combinations of:

- lecture
- room
- time slot

If a placement does not work, the program goes back and tries a different option.

Constraint checking is also linear in the number of scheduled lectures because the program scans the timetable to detect clashes.

So the overall worst case is expensive, but that is expected for a backtracking solution.

---

## Learning Outcomes

This project helped me understand:

- how Java classes work together in a real program
- how to use constructors, getters, and `toString()`
- how Backtracking solves problems step by step
- why constraints matter in scheduling problems
- how to split a program into smaller classes with clear responsibilities
- how to use Java Collections like `List`, `ArrayList`, and `Collections.shuffle()`
- how to keep code readable instead of making it too complicated

I also learned that good program design is not just about making code work.

It is also about making the code easy to understand and easy to change later.

---

## Possible Improvements

This project is complete for a console-based learning version, but it could be improved in a few simple ways:

- add a GUI
- save timetable data to a file
- export timetable to PDF or CSV
- use a database instead of keeping everything in memory
- add faculty availability preferences
- improve scheduling heuristics so the algorithm finds solutions faster
- add support for more room types or more days if needed

These ideas are useful future upgrades, but I kept the current version simple on purpose.

---

## How to Run

### Option 1: One-click run on Windows

1. Open the project folder.
2. Double-click `Run.bat`.
3. The script compiles the Java files and starts the program.

### Option 2: Manual run

1. Open a terminal inside the project folder.
2. Compile the source files:

```bash
javac -d out src/*.java
```

3. Run the program:

```bash
java -cp out Main
```

### Before running

- Make sure Java 17 is installed.
- Make sure `java` and `javac` are available in your PATH.
- On first run, the program will ask for subject details.

---

## Author

Author: Your Name

---

## Notes

- This is a learning project.
- The code is intentionally kept simple and beginner-friendly.
- The focus is on OOP, Backtracking, and readable design.

---

## Folder Summary

- `src/` contains the Java source code
- `Run.bat` runs the application on Windows with one click
- `README.md` explains the project and how to use it

---

## Input Format

When the program starts, it asks for:

- number of subjects
- subject name
- faculty name
- lectures per week
- whether the subject is a lab

After that, the menu lets you:

- generate a timetable
- generate a new timetable
- exit the application

---

## Default Rooms

The program automatically creates these rooms:

- `R101` - normal classroom
- `R102` - normal classroom
- `R103` - normal classroom
- `R104` - normal classroom
- `LAB1` - laboratory

---

## Default Time Slots

The program uses these lecture slots:

- 8:00 - 9:00
- 9:00 - 10:00
- 10:00 - 11:00
- 11:00 - 12:00
- 12:30 - 1:30
- 1:30 - 2:30

Lunch break is not included.

---

## Final Thought

This project was a good exercise in combining Java OOP with a classic Backtracking problem.

It is not a huge system, but it shows how a simple idea can be built in a clean and structured way.

