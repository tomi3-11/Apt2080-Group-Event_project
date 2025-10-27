from app import create_app
from dotenv import load_dotenv
from app import db


app = create_app()

load_dotenv()

# Entry point
if __name__ == "__main__":
    with app.app_context():
        db.create_all()
    app.run(port=5000, host="0.0.0.0")