# Evenify
## Overview

The **Event Management System** is a Flask-based web application designed to streamline the creation, management, and participation in various events. The project provides user authentication, event registration, and an administrative workflow for managing event-related activities. It follows a modular structure for maintainability and scalability.

here are the Group members

| Full Name | Student ID | GitHub Username |
|------------|------------|-----------------|
| Thomas Wotoro | 674609 | tomi3-11 |
| Rayan Nur  | 671479 | rayanmohamed7 |
| Precious Zawadi | 673738 | crispl3afy |
| Rose Maina | 671834 | Sige001 |
| Winnie Kihaya | 666067 | winnie-ms |
| Kate Maina | 672929 | igenuinelyliketocode |
| Cyrus Mwangi | 674001 | 4cyr |
| Mohamud abdi | 671877 | lamahelanpoi |



# Project Title: Event Management System

## Features

* **User Authentication** – Register, login, and manage user sessions securely.
* **Event Management** – Create, update, and delete events through the admin interface.
* **Registration System** – Allow users to register for events seamlessly.
* **Blueprint Architecture** – Organized structure using Flask Blueprints for modular development.
* **Automated Testing** – Includes unit tests to ensure the stability of key functionalities.
* **CI/CD Workflow** – Integrated GitHub Actions workflows for automated testing and deployment.

## Project Structure

```python
├── .github/workflows     # CI/CD workflow configuration files
├── app                   # Core Flask application code (routes, models, blueprints)
├── images                # Static images or assets for the project
├── tests                 # Unit and integration test cases
├── .gitignore            # Files and directories to ignore in Git
├── README.md             # Project documentation
├── config.py             # Configuration settings for Flask app
├── requirements.txt      # Python dependencies
└── run.py                # Application entry point
```

## Installation

### Prerequisites

* Python 3.8 or higher
* pip (Python package manager)

### Steps

1. **Clone the repository**

   ```bash
   git https://github.com/tomi3-11/Apt2080-Group-Event_project.git
   cd Apt2080-Group-Event_project
   ```

2. **Create and activate a virtual environment**

   ```bash
   python -m venv venv
   source venv/bin/activate  # On Windows: venv\Scripts\activate
   ```

3. **Install dependencies**

   ```bash
   pip install -r requirements.txt
   ```

4. **Run the application**

   ```bash
   python run.py
   ```

5. **Access the app**
   Open your browser and go to `http://127.0.0.1:5000/`

## Running Tests

To run tests and verify functionality:

```bash
pytest
```

## Configuration

All configurable variables (e.g., database URI, secret key) are stored in `config.py`. You can modify them according to your environment.

## GitHub Actions Workflow

The project includes a GitHub Actions pipeline for:

* Running tests automatically on every push.
* Ensuring code quality and deployment consistency.

## Future Enhancements

* Add user roles (Admin, Organizer, Participant)
* Implement email notifications for event updates
* Integrate database migrations using Flask-Migrate
* Add RESTful API endpoints for mobile or external app integration

## Contributing

1. Fork the repository
2. Create your feature branch: `git checkout -b feature/YourFeature`
3. Commit your changes: `git commit -m 'Add new feature'`
4. Push to the branch: `git push origin feature/YourFeature`
5. Open a Pull Request



# How to contribute
# 1. Clone the repository
```bash
git clone https://github.com/tomi3-11/Apt2080-Group-Event_project.git
```

# 2. Change the directory
```bash
cd Apt2080-Group-Event_project
```

# 3. Create a virtual environment
```bash
# Create virtual environment
# Windows
python -m venv venv
venv\Scripts\activate

# Mac/linux
python3 -m venv venv
source venv/bin/activate
```

# 4. install the dependencies
```bash
pip install -r requirements.txt

```
# 5. Run the application
```bash
python run.py # Windows

python3 run.py # Mac/Linux

```



## License

This project is licensed under the [MIT License](LICENSE).

## Author

**Thomas Jose (tomi3-11)** <br>
Flask Developer | Software Engineering Student <br>
[Contact via GitHub Profile](https://github.com/tomi3-11) <br>



