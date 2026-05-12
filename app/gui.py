import tkinter as tk
from tkinter import ttk
from .logic import neue_aufgabe, pruefe_antwort
from .stats import load_highscore, save_highscore, update_chart
from .speech import speak


class MatheTrainer:

    def __init__(self, root):

        self.root = root
        self.root.title("Mathe Trainer")
        self.root.geometry("900x700")

        self.max_wert = tk.IntVar(value=10)
        self.operator = tk.StringVar(value="+")

        self.richtig = 0
        self.falsch = 0
        self.highscore = load_highscore()

        self.zahl_1 = 0
        self.zahl_2 = 0
        self.ergebnis = 0

        self.build_ui()
        self.neue_aufgabe()

    def build_ui(self):

        ttk.Label(self.root, text="Mathe Trainer", font=("Arial", 24)).pack()

        self.frage = ttk.Label(self.root, font=("Arial", 28))
        self.frage.pack(pady=20)

        self.entry = ttk.Entry(self.root, font=("Arial", 18))
        self.entry.pack()

        ttk.Button(self.root, text="Check", command=self.check).pack()

    def neue_aufgabe(self):
        self.zahl_1, self.zahl_2, self.ergebnis = neue_aufgabe(
            self.max_wert.get(),
            self.operator.get()
        )

        self.frage.config(text=f"{self.zahl_1} {self.operator.get()} {self.zahl_2}")

        speak(self.frage.cget("text"))

    def check(self):

        result = pruefe_antwort(
            self.entry.get(),
            self.ergebnis
        )

        if result:
            self.richtig += 1
            speak("richtig")
        else:
            self.falsch += 1
            speak("falsch")

        self.entry.delete(0, tk.END)
        self.neue_aufgabe()


def start_app():
    root = tk.Tk()
    MatheTrainer(root)
    root.mainloop()