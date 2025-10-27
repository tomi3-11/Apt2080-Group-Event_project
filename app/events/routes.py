from flask import Blueprint, render_template, redirect, url_for, flash, request
from flask_login import login_required, current_user
from datetime import datetime
from app import db
from app.models import Event, Registration
from .forms import EventForm

event_bp = Blueprint('event', __name__)


# List all events
@event_bp.route('/')
def index():
    events = Event.query.order_by(Event.date.asc()).all()
    return render_template('index.html', user=current_user, events=events, title="Home")


# Create new event
@event_bp.route('/create', methods=['GET', 'POST'])
@login_required
def create_event():
    form = EventForm()
    if form.validate_on_submit():
        new_event = Event(
            title=form.title.data,
            description=form.description.data,
            date=form.date.data,
            time=form.time.data,
            location=form.location.data,
            max_attendees=form.max_attendees.data,
            created_by=current_user.id
        )
        db.session.add(new_event)
        db.session.commit()
        flash('Event created successfully!', 'success')
        return redirect(url_for('event.index'))
    return render_template('events/create_event.html', form=form, title="Create Event")


# View event details
@event_bp.route('/<int:event_id>')
def event_detail(event_id):
    event = Event.query.get_or_404(event_id)
    attendees_count = Registration.query.filter_by(event_id=event_id).count()
    is_registered = False
    if current_user.is_authenticated:
        is_registered = Registration.query.filter_by(
            event_id=event_id, user_id=current_user.id
        ).first() is not None
    return render_template(
        'events/event_detail.html',
        event=event,
        attendees_count=attendees_count,
        is_registered=is_registered,
        title=event.title
    )


# Register for an event
@event_bp.route('/<int:event_id>/register', methods=['POST'])
@login_required
def register_event(event_id):
    event = Event.query.get_or_404(event_id)

    existing = Registration.query.filter_by(event_id=event_id, user_id=current_user.id).first()
    if existing:
        flash('You are already registered for this event.', 'info')
    else:
        count = Registration.query.filter_by(event_id=event_id).count()
        if count >= event.max_attendees:
            flash('Registration full! No more seats available.', 'warning')
        else:
            registration = Registration(user_id=current_user.id, event_id=event_id)
            db.session.add(registration)
            db.session.commit()
            flash('Successfully registered for the event!', 'success')
    return redirect(url_for('event.event_detail', event_id=event_id))

