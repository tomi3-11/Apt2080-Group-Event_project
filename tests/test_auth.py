def test_register_user(client):
    response = client.post('/register', data={
        'username': 'testuser',
        'email': 'test@example.com',
        'password': 'password'
    }, follow_redirects=True)
    assert response.status_code == 200

def test_login_user(client):
    # First register
    client.post('/register', data={
        'username': 'testuser',
        'email': 'test@example.com',
        'password': 'password'
    })
    # Then login
    response = client.post('/login', data={
        'email': 'test@example.com',
        'password': 'password'
    }, follow_redirects=True)
    assert response.status_code == 200
