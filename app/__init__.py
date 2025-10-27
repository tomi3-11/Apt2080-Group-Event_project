import os
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_login import LoginManager
from config import Config

db = SQLAlchemy()
login_manager = LoginManager()

def create_app(config_class=Config):
    app = Flask(__name__)
    
    if config_class is None:
        app.config.from_object(os.environ.get("FLASK_CONFIG", "default"))
    else:
        app.config.from_object(config_class)
    
    db.init_app(app)
    login_manager.init_app(app)
    login_manager.login_view = 'auth.login'
    
    from .models import User
    
    @login_manager.user_loader
    def load_user(id):
        return User.query.get(int(id))
    
    # Register Blueprints
    from .auth.routes import auth_bp
    from .events.routes import event_bp
    
    app.register_blueprint(auth_bp)
    app.register_blueprint(event_bp)
    
    return app
