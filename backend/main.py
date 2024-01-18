# flask --app hello run

from flask import Flask

app = Flask(__name__)

# TODO: Create the necessary endpoints

@app.route("/")
def entry():
    return "Hello, World!"