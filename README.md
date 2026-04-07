# BYOW (Build Your Own World)

A procedurally generated 2D dungeon world built in Java.  
The project focuses on turning a random seed into a fully playable world, including world generation, interaction, and basic AI behavior.

---

## Overview

The program generates a **random dungeon made of rooms and corridors**, then lets the player explore it in real time.

The whole world is reproducible using a seed, and everything from layout to paths is generated algorithmically.

---

## World Generation

### Random Rooms

- Rooms are generated with random position and size
- Overlap is checked during generation
- Invalid rooms are discarded and regenerated

---

### Room Connectivity (MST)

- Each room is treated as a point (using its center)
- Distances between rooms are used as edge weights
- **Kruskal’s algorithm** is used to build a Minimum Spanning Tree

Result:
- All rooms are connected
- No unnecessary loops

---

### Corridors

- Each connection is converted into an **L-shaped path**
- Paths are represented as a sequence of grid coordinates

---

### Tile Application

- Each coordinate along the corridor is applied to the world:
  - Empty tile → becomes floor
  - Existing floor → left unchanged

- Neighbor tiles are checked to ensure paths connect cleanly

---

## Design (3-Layer Structure)

The world generation is split into three layers:

### 1. Structure Layer

- Decides **which rooms should be connected**
- Uses MST (Kruskal) to define relationships

👉 Output: abstract connections (room-to-room)

---

### 2. Geometry Layer

- Converts connections into **actual paths**
- Uses L-shaped (Manhattan) corridors

👉 Output: sequences of coordinates

---

### 3. Implementation Layer

- Applies coordinates to the tile grid
- Handles floor / empty tile logic
- Ensures paths are physically connected

👉 Output: final playable map

---

## Interaction

### Menu

- `N` — New Game  
- `L` — Load Game  
- `Q` — Quit  

---

### Seed Input

- Players can input a numeric seed (up to 18 digits)
- Press `Enter` or `S` to start

---

### In-Game Controls

- Keyboard input controls player movement
- `:Q` saves and exits the game

---

### HUD

- Mouse position is tracked continuously
- Hover over a tile for ~1 second
- Tile description is displayed at the top-left

---

## Systems

### Monster Pathfinding (A*)

- Monsters use **A\*** to chase the player
- Target updates dynamically

---

### Lighting (BFS)

- Light spreads from sources using **BFS**

---

### Audio

- Background music and sound effects
- Implemented using Java’s built-in audio system

---

## Tech Stack

- Java
- StdDraw
- Algorithms:
  - Kruskal (MST)
  - A\* (pathfinding)
  - BFS (lighting)

---

## photo
<img width="2203" height="1232" alt="屏幕截图 2026-04-07 223351" src="https://github.com/user-attachments/assets/ff14774d-ae05-4844-8b6b-6a07f58fde05" />


## Project Info

This project is the final assignment for:

> **CS61B — BYOW (Build Your Own World)**
