from flask_wtf import FlaskForm
from wtforms import StringField, TextAreaField, DateField, TimeField, IntegerField, SubmitField
from wtforms.validators import DataRequired, Length, NumberRange

class EventForm(FlaskForm):
    title = StringField('Title', validators=[DataRequired(), Length(max=100)])
    description = TextAreaField('Description', validators=[DataRequired()])
    date = DateField('Date', validators=[DataRequired()])
    time = TimeField('Time', validators=[DataRequired()])
    location = StringField('Location', validators=[DataRequired(), Length(max=150)])
    max_attendees = IntegerField('Max Attendees', validators=[DataRequired(), NumberRange(min=1)])
    submit = SubmitField('Create Event')
