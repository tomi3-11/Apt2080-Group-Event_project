def test_register_user(client):
    response = client.post('/register', data={
        "username": "newuser",
        "email": "new@example.com",
        "password": "test123",
        "confirm_password": "test123"
    }, follow_redirects=True)
    
    assert response.status_code == 200
    assert b"Login" in response.data
    

def test_login_user(client, new_user):
    response = client.post("/login", data={
        "username": "testuser",
        "password": "password123"
    }, follow_redirects=True)
    
    assert response.status_code == 200
    assert b"Logout" in response.data