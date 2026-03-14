# 🧠 Flip Flop – Memory Matching Game

**Flip Flop** is a **fast-paced memory training mobile game** built natively for **Android using Java**.  
Players must **identify matching pairs within an 8×8 grid before time runs out**, making it a fun and challenging way to test and improve memory skills. ⏳🎮

---

## ✨ Features

### 🧩 Large Interactive Game Board
- **8×8 grid layout** consisting of **64 interactive cards**.
- Built using **MaterialButton** and **GridLayout** to ensure a **clean, responsive, and modern interface**. 📱✨

### 🎲 Dynamic Matching Logic
- The game generates **32 unique number pairs**.
- Cards are **randomly shuffled and reassigned every time the game starts**, ensuring a new challenge on each play. 🔀🎲

### ⏱️ Race Against Time
- Includes a **120-second countdown timer** that adds urgency and excitement to the gameplay.
- Players must **find all matching pairs before the timer expires**. 🏃💨

### 🔄 Auto-Reset Gameplay
- When the **timer reaches zero**, the board **automatically reshuffles and restarts**, enabling **continuous gameplay** without manual reset. 🔁⚡

### 👀 Visual Feedback System
- If two selected cards **do not match**, they remain visible for **1 second** before flipping back.
- This delay mimics the behavior of **classic memory games**, giving players time to memorize card positions. 🧠✨

---

## 🛠️ Technology Stack

- **Programming Language:** Java ☕💻  
- **Framework:** Android SDK *(AppCompat, Material Components)* 🤖📦  
- **UI Components:**  
  - `GridLayout` for the **game board structure**  
  - `MaterialButton` for the **interactive cards** 🖼️  
- **Utilities:**  
  - `Handler` for **flip delay timing**  
  - `CountDownTimer` for **managing game duration** ⚙️  

---

## 🚀 How to Play

1. **Tap a Card**  
   Select a card to **reveal the hidden number** beneath it.

2. **Find the Matching Pair**  
   Tap another card to try to **find the matching number**.

3. **Matching Result**  
   - ✅ If the cards **match**, they remain visible.  
   - ❌ If they **do not match**, they flip back after a short delay.

4. **Beat the Clock**  
   Match **all 32 pairs before the 120-second timer ends** to win the game. ⏳🏆



⭐ If you enjoy this project, consider **starring the repository**!
