import pytest
from app import create_app, db

@pytest.fixture()
def app():
    """Create and configure a new app instance for each test."""
    app = create_app({
        "TESTING": True,
        "SQLALCHEMY_DATABASE_URI": "sqlite:///:memory:", 
        "SQLALCHEMY_TRACK_MODIFICATIONS": False,
        "WTF_CSRF_ENABLED": False
    })

    # Create all tables before each test
    with app.app_context():
        db.create_all()
        yield app
        db.session.remove()
        db.drop_all()

@pytest.fixture()
def client(app):
    """Return a test client for the app."""
    return app.test_client()

@pytest.fixture()
def runner(app):
    """Return a CLI test runner for the app."""
    return app.test_cli_runner()
