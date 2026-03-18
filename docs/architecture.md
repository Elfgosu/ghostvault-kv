## Overview

This project is a key-value store consisting of:

- Java GUI
- JNI bridge
- C-based storage engine

The system is a learning project to explore storage engines,
log-structured (and hopefully other) storage, and cross-language integration.

### Contents

1. High-Level Architecture
2. Component Design
3. Storage Engine
4. Data Format
5. Short-Term Planned Enhancements
6. Long-Term Exploration of Designs

---

## 1. High-Level Architecture

**Java GUI** ⇄ **JNI Bridge** ⇄ **C Storage Engine**

---

## 2. Component Design (Work in Progress)

### Java Gui

### JNI Bridge

### C Storage Engine

---

## 3. Storage Engine (Work in Progress)

---

## 4. Data Format (Work in Progress)

- Record layout (See also [log-format.md](./log-format.md))
- Headers
- Key/value encoding

---

## 5. Short-Term Planned Enhancements

- Segmentation
- Tombstoning
- Compaction

---

## 6. Long-Term Exploration of Designs

The following approaches may be explored in the future:
- LSM Trees
- B-Trees

See also [roadmap.md](./roadmap.md) for more information.