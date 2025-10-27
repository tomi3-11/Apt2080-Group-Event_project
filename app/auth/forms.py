from wtforms import PasswordField, EmailField, StringField, SubmitField
from wtforms.validators import Email, EqualTo, DataRequired, Length
from flask_wtf import FlaskForm


# Registration form
class RegistrationForm(FlaskForm):
    username = StringField("User Name", validators=[DataRequired(), Length(min=2)])
    email = EmailField("Email", validators=[DataRequired(), Email()])
    password = PasswordField("Password", validators=[DataRequired()])
    confirm_password = PasswordField(
        "Confirm Password",
        validators=[DataRequired(), EqualTo('password', message="Passwords must match.")]
    )
    submit = SubmitField("Register")


# Login form
class LoginForm(FlaskForm):
    username = StringField("User Name", validators=[DataRequired(), Length(min=2)])
    password = PasswordField("Password", validators=[DataRequired()])
    submit = SubmitField("Login")
