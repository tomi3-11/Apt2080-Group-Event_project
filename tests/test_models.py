from app.models import Event, Registration, User
from datetime import date, time, datetime

def test_create_user(new_user):
    assert new_user.username == "testuser"
    assert new_user.email == "test@example.com"
    assert new_user.check_password("password123")

def test_create_event(sample_event):
    assert sample_event.title == "Test Event"
    assert sample_event.location == "Nairobi"
    assert isinstance(sample_event.date, date)
    assert isinstance(sample_event.time, time)
