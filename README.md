# 2D Adventure Game

A 2D top-down adventure game built with Java Swing, featuring tile-based collision detection, NPC AI, dialogue systems, and a state-driven game loop.

---

> **In Progress** — core engine and gameplay systems are functional; content (objects, items, levels) is still being added.

---

## Features

- **Tile-based world rendering** with a 50×50 world map and 16×12 tile viewport
- **Axis-separated collision detection** for tiles, NPCs, and objects (prevents corner-clipping)
- **NPC AI** with randomised movement and activates within player's proximity
- **Dialogue system** with multi-line text and state-based UI
- **Game state machine** (Title, Play, Pause, Dialogue) driving input and rendering
- **Camera system** that follows the player with world-to-screen coordinate conversion

---

## Architecture

| Class | Responsibility |
|---|---|
| `Main` | Entry point; creates the window and starts the game |
| `GamePanel` | Game loop, rendering, and system wiring |
| `KeyHandler` | Keyboard input; state-aware actions |
| `CollisionChecker` | Axis-separated AABB collision for tiles, NPCs, and objects |
| `TileManager` | Tile map loading and rendering |
| `AssetPlacer` | Places NPCs and objects in the world |
| `UI` | Title, pause, and dialogue screens |
| `Sound` | Music and sound effect playback |
| `GameSettings` | Centralized constants (tile size, screen size, FPS) |
| `entity.Entity` | Base class for player and NPCs (movement, animation, drawing) |
| `entity.Player` | Player movement, input handling, and interaction |
| `entity.OldMan` | Example NPC with randomized AI and dialogue |
| `object.GameObject` | Base class for interactive world objects |
| `tile.Tile` / `TileManager` | Tile definitions and map data |
| `GameState` / `Direction` | Enums for game state and facing direction |

---

## Technical Highlights

- **Fixed-timestep game loop** targeting 60 FPS using `System.nanoTime()` with delta accumulation
- **Axis-separated collision** — checks X and Y movement independently so the player slides along walls instead of sticking
- **Diagonal movement normalisation** — multiplies by `0.7071` so diagonal speed matches cardinal speed
- **Spatial culling** — only draws entities within the camera viewport
- **Enum-driven state machines** throughout for game state and direction handling

---

## Controls

| Key | Action |
|---|---|
| `W` / `A` / `S` / `E` | Move up / left / down / right |
| `Shift` | Run |
| `Enter` | Confirm / advance dialogue |
| `Esc` | Pause / resume |

---

## Acknowledgements

This project is for personal portfolio use.

The initial project structure was based on RyiSnow's Java 2D game tutorial
(4-direction movement, basic tile rendering). My modifications include:

- **8-direction movement** with diagonal speed normalisation
- **Axis-separated collision detection** for wall-sliding
- Distance-squared proximity culling for NPC/object activation
- Refactored entity hierarchy and state-driven dialogue/UI