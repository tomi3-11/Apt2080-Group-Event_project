def test_index_route(client):
    response = client.get('/')
    assert response.status_code == 200
    assert b"Welcome to" in response.data 
    assert b"Evenify" in response.data 

def test_create_event_requires_login(client):
    response = client.post('/create', data={
        'title': 'Hackathon',
        'description': 'A cool event'
    })
    assert response.status_code in (302, 401) 
