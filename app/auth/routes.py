from flask import Blueprint, redirect, render_template, flash, request, url_for
from .forms import RegistrationForm, LoginForm
from ..models import User
from .. import db
from flask_login import login_user, logout_user

auth_bp = Blueprint('auth', __name__)

# Registration route
@auth_bp.route('/register', methods=['GET', 'POST'])
def register():
    form = RegistrationForm()
    
    if request.method == "POST" and form.validate_on_submit():
        user = User.query.filter(
            (User.email == form.email.data) | (User.username == form.username.data)
        ).first()
        
        if user:
            flash("Username or email already exists.")
            return redirect(url_for('auth.register'))
        
        new_user = User(
            username=form.username.data,
            email=form.email.data
        )
        new_user.set_password(form.password.data)
        
        db.session.add(new_user)
        db.session.commit()
        
        flash("Registered successfully! You can now log in.")
        return redirect(url_for('auth.login'))
        
    return render_template('register.html', form=form, title="Registration")

# Login route
@auth_bp.route('/login', methods=["GET", "POST"])
def login():
    form = LoginForm()
    
    if request.method == "POST" and form.validate_on_submit():
        user = User.query.filter_by(username=form.username.data).first()
        
        if not user or not user.check_password(form.password.data):
            flash("Invalid username or password.")
            return redirect(url_for('auth.login'))
        
        login_user(user)
        flash("Login successful! Redirecting...")
        return redirect(url_for('event.index'))
        
    return render_template('login.html', form=form, title="Login")


@auth_bp.route('/logout', methods=["GET", "POST"])
def logout():
    logout_user()
    flash("You have been logged out.")
    return redirect(url_for('auth.login'))