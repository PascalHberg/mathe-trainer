import json
import os


FILE = "data/highscore.json"


def load_highscore():

    if not os.path.exists(FILE):
        return 0

    try:
        with open(FILE, "r") as f:
            return json.load(f).get("highscore", 0)
    except:
        return 0


def save_highscore(value):

    os.makedirs("data", exist_ok=True)

    with open(FILE, "w") as f:
        json.dump({"highscore": value}, f)


def update_chart():
    # später aus GUI ausgelagert
    pass