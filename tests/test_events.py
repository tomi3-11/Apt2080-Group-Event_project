from flask import url_for

def test_index_route(client):
    """Ensure index page loads successfully."""
    response = client.get("/")
    assert response.status_code == 200
    assert b"Evenify" in response.data

def test_create_event_requires_login(client):
    """Ensure guests cannot access create event page."""
    response = client.get("/create", follow_redirects=True)
    assert b"Login" in response.data or response.status_code in [302, 401]

def test_create_event_logged_in(app, client, new_user):
    """Ensure logged-in user can create an event."""
    with client:
        client.post("/login", data={"username": "testuser", "password": "password123"})
        response = client.post("/create", data={
            "title": "Flask Meetup",
            "description": "Learn Flask together.",
            "date": "2025-11-01",
            "time": "15:00",
            "location": "Nairobi",
            "max_attendees": 50
        }, follow_redirects=True)
        assert response.status_code == 200
        assert b"Flask Meetup" in response.data
