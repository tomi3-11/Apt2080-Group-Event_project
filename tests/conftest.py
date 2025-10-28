import pytest 
from app import create_app, db
from ..app.models import User, Event
from datetime import date, time


@pytest.fixture
def app():
    app = create_app({
        "TESTING": True,
        "SQLALCHEMY_DATABASE_URI": "sqlite:///:memory:",
        "WTF_CSRF_ENABLED": False,
        "LOGIN_DISABLED": False
    })
    
    with app.app_context():
        db.create_all()
        yield app
        db.session.remove()
        db.drop_all()
        

@pytest.fixture
def client(app):
    return app.test_cli_client()


@pytest.fixture
def runner(app):
    return app.test_cli_client()


@pytest.fixture
def new_user(app):
    user = User(username="testuser", email="test@example.com")
    user.set_password("password123")
    db.session.add(user)
    db.session.commit()
    return user


@pytest.fixture
def sample_event(app, new_user):
    event = Event(
        title="Test Event",
        description="This is a test event.",
        date=date.today(),
        time=time(10, 30),
        location="Nairobi",
        max_attendees=100,
        created_by=new_user
    )
    db.session.add(event)
    db.session.commit()
    return event