import random


def neue_aufgabe(max_wert, op):

    if op == "+":

        a = random.randint(1, max_wert)
        b = random.randint(1, max_wert)
        return a, b, a + b

    if op == "-":

        a = random.randint(1, max_wert)
        b = random.randint(1, a)
        return a, b, a - b

    if op == "*":

        a = random.randint(1, max(5, max_wert // 2))
        b = random.randint(1, max(5, max_wert // 2))
        return a, b, a * b