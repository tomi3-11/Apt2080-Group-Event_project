import os

class Config:
    if os.environ.get('DATABASE_URL'):
        SQLALCHEMY_DATABASE_URI = os.environ.get('DATABASE_URL')
    else:
        # For local develpement with SQLite
        basedir = os.path.abspath(os.path.dirname(__file__))
        SQLALCHEMY_DATABASE_URI = 'sqlite:///' + os.path.join(basedir, 'app.sql') 
        
    SQLALCHEMY_TRACK_MODIFICATION = False
    
    SECRET_KEY = os.environ.get("SECRET_KEY") or "my_SeCrEt_keY"
    
    
class DevelopmentConfiguration(Config):
    debug = True