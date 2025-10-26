from app import create_app
from dotenv import load_dotenv


app = create_app()

load_dotenv()

# Entry point
if __name__ == "__main__":
    app.run(port=5000, host="0.0.0.0")