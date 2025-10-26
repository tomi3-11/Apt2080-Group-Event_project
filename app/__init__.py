import os
from flask_sqlachemy import SQLAlchemy
from flask import Flask
from config import Config

db = SQLAlchemy()

def create_app(config_class=Config):
    app = Flask(__name__)
    
    if config_class is None:
        app.config_class.from_object = os.environ.get("FLASK_CONFIG", "default")
    else:
        app.config_class.from_object(config_class)
    
    db.init_app(app)
    
    
    # Registering our Blueprints
    
    
    return app