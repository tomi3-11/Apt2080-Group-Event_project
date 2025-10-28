from app.models import User, Event

def test_create_user(app):
    with app.app_context():
        user = User(username='test', email='a@b.com')
        assert user.username == 'test'
        assert user.email == 'a@b.com'

def test_create_event(app):
    with app.app_context():
        event = Event(title='Demo', description='Testing')
        assert event.title == 'Demo'
