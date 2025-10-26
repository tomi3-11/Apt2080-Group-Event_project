from werkzeug.security import check_password_hash, generate_password_hash
from app import db
from datetime import datetime


# User entity
class User(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(30), nullable=False, unique=True)
    email = db.Column(db.String(50), nullable=False, unique=True)
    password_hash = db.Column(db.String(120))
    role = db.Column(db.String(30), default='user')
    
    # Set password
    def set_password(self, password):
        self.password_hash = generate_password_hash(password)
    
    # Check password    
    def check_password(self, password):
        return check_password_hash(self.password_hash, password)
    

# Events entity
class Event(db.Model):
    event_id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(100))
    description = db.Column(db.Text)
    date = db.Column(db.Date)
    time = db.Column(db.Time)
    location = db.Column(db.String(150))
    max_attendees = db.Column(db.Integer)
    created_by = db.Column(db.Integer, db.ForeignKey('user.user_id'))

# Registreation entity
class Registration(db.Model):
    registration_id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.user_id'))
    event_id = db.Column(db.Integer, db.ForeignKey('event.event_id'))
    registration_date = db.Column(db.DateTime, default=datetime.utcnow)
        

    