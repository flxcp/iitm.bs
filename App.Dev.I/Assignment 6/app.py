from flask import Flask, make_response
from flask_restful import Api, Resource, fields, marshal_with, reqparse
from flask_sqlalchemy import SQLAlchemy
from werkzeug.exceptions import HTTPException
import json

app = Flask(__name__)
api = Api(app)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///api_database.sqlite3'
db = SQLAlchemy()
db.init_app(app)
app.app_context().push()

# Declare Model


class Student(db.Model):
    __tablename__ = 'student'
    student_id = db.Column(db.Integer, autoincrement=True, primary_key=True)
    roll_number = db.Column(db.String, unique=True, nullable=False)
    first_name = db.Column(db.String, nullable=False)
    last_name = db.Column(db.String)


class Course(db.Model):
    __tablename__ = 'course'
    course_id = db.Column(db.Integer, autoincrement=True, primary_key=True)
    course_code = db.Column(db.String, unique=True, nullable=False)
    course_name = db.Column(db.String, nullable=False)
    course_description = db.Column(db.String)


class Enrollment(db.Model):
    __tablename__ = 'enrollment'
    enrollment_id = db.Column(db.Integer, autoincrement=True, primary_key=True)
    student_id = db.Column(db.Integer, db.ForeignKey(
        'student.student_id'), nullable=False)
    course_id = db.Column(db.Integer, db.ForeignKey(
        'course.course_id'), nullable=False)

# Define Errors


class NotFoundError(HTTPException):
    def __init__(self, status_code):
        self.response = make_response('', status_code)


class BusinessValidationError(HTTPException):
    def __init__(self, status_code, error_code, error_message):
        message = {'error_code': error_code, 'error_message': error_message}
        self.response = make_response(json.dumps(message), status_code)

####### COURSE API #######


# Request Parser JSON
course_request_parse = reqparse.RequestParser()
course_request_parse.add_argument('course_name')
course_request_parse.add_argument('course_code')
course_request_parse.add_argument('course_description')

# API Response Field
course_response_fields = {
    "course_id": fields.Integer,
    "course_name": fields.String,
    "course_code": fields.String,
    "course_description": fields.String
}


class CourseAPI(Resource):
    @marshal_with(course_response_fields)
    def get(self, course_id):
        course_details = db.session.query(
            Course).filter(Course.course_id == course_id).first()

        if course_details:
            return course_details
        else:
            raise NotFoundError(status_code=404)

    def delete(self, course_id):
        courses_exist = db.session.query(
            Course).filter(Course.course_id == course_id).first()

        if courses_exist:

            db.session.delete(courses_exist)
            db.session.commit()

            return '', 200

        if courses_exist is None:
            raise NotFoundError(status_code=404)

    @marshal_with(course_response_fields)
    def put(self, course_id):
        args = course_request_parse.parse_args()
        course_name = args.get('course_name', None)
        course_code = args.get('course_code', None)
        course_description = args.get('course_description', None)

        if course_name is None:
            raise BusinessValidationError(
                status_code=400, error_code='COURSE001', error_message='Course Name is required and should be string')

        if course_code is None:
            raise BusinessValidationError(
                status_code=400, error_code='COURSE002', error_message='Course Code is required and should be string')

        if course_description is None:
            raise BusinessValidationError(
                status_code=400, error_code='COURSE003', error_message='Course Description should be string')

        course = db.session.query(Course).filter(
            (Course.course_id == course_id)).first()

        if course is None:
            raise NotFoundError(status_code=404)

        course.course_name = course_name
        course.course_code = course_code
        course.course_description = course_description

        db.session.add(course)
        db.session.commit()

        return course

    @marshal_with(course_response_fields)
    def post(self):
        args = course_request_parse.parse_args()
        course_name = args.get('course_name', None)
        course_code = args.get('course_code', None)
        course_description = args.get('course_description', None)

        if course_name is None:
            raise BusinessValidationError(
                status_code=400, error_code='COURSE001', error_message='Course Name is required and should be string')

        if course_code is None:
            raise BusinessValidationError(
                status_code=400, error_code='COURSE002', error_message='Course Code is required and should be string')

        if course_description is None:
            raise BusinessValidationError(
                status_code=400, error_code='COURSE003', error_message='Course Description should be string')

        course = db.session.query(Course).filter(
            (Course.course_code == course_code) | (Course.course_name == course_name)).first()

        if course:
            raise NotFoundError(status_code=409)

        new_course = Course(course_code=course_code, course_name=course_name,
                            course_description=course_description)
        db.session.add(new_course)
        db.session.commit()

        return new_course, 201


####### STUDENT API #######

# Request Parser JSON
student_request_parse = reqparse.RequestParser()
student_request_parse.add_argument('first_name')
student_request_parse.add_argument('last_name')
student_request_parse.add_argument('roll_number')

# API Response Field
student_response_fields = {
    "student_id": fields.Integer,
    "first_name": fields.String,
    "last_name": fields.String,
    "roll_number": fields.String
}


class StudentAPI(Resource):
    @marshal_with(student_response_fields)
    def get(self, student_id):
        student_details = db.session.query(
            Student).filter(Student.student_id == student_id).first()

        if student_details:
            return student_details
        else:
            raise NotFoundError(status_code=404)

    def delete(self, student_id):
        student_exist = db.session.query(
            Student).filter(Student.student_id == student_id).first()

        if student_exist:

            db.session.delete(student_exist)
            db.session.commit()

            return '', 200
        else:
            raise NotFoundError(status_code=404)

    @marshal_with(student_response_fields)
    def put(self, student_id):
        args = student_request_parse.parse_args()
        first_name = args.get('first_name', None)
        last_name = args.get('last_name', None)
        roll_number = args.get('roll_number', None)

        if first_name is None:
            raise BusinessValidationError(
                status_code=400, error_code='STUDENT001', error_message='First Name is required and should be string')

        if last_name is None:
            raise BusinessValidationError(
                status_code=400, error_code='STUDENT002', error_message='Last name should be string')

        if roll_number is None:
            raise BusinessValidationError(
                status_code=400, error_code='STUDENT003', error_message='Roll Number is required and should be string')

        student = db.session.query(Student).filter(
            (Student.student_id == student_id)).first()

        if student is None:
            raise NotFoundError(status_code=404)

        student.first_name = first_name
        student.last_name = last_name
        student.roll_number = roll_number

        db.session.add(student)
        db.session.commit()

        return student

    @marshal_with(student_response_fields)
    def post(self):
        args = student_request_parse.parse_args()
        first_name = args.get('first_name', None)
        last_name = args.get('last_name', None)
        roll_number = args.get('roll_number', None)

        if first_name is None:
            raise BusinessValidationError(
                status_code=400, error_code='STUDENT001', error_message='First Name is required and should be string')

        if last_name is None:
            raise BusinessValidationError(
                status_code=400, error_code='STUDENT002', error_message='Last name should be string')

        if roll_number is None:
            raise BusinessValidationError(
                status_code=400, error_code='STUDENT003', error_message='Roll Number is required and should be string')

        student = db.session.query(Student).filter(
            (Student.roll_number == roll_number)).first()

        if student:
            raise NotFoundError(status_code=409)

        new_student = Student(first_name=first_name, last_name=last_name,
                              roll_number=roll_number)
        db.session.add(new_student)
        db.session.commit()

        return new_student, 201

####### ENROLLMENT API #######


# API Response Field
enrollment_response_fields = {
    "enrollment_id": fields.Integer,
    "student_id": fields.Integer,
    "course_id": fields.Integer
}


class EnrollmentAPI(Resource):
    @marshal_with(enrollment_response_fields)
    def get(self, student_id):
        student_exist = db.session.query(
            Student).filter(Student.student_id == student_id).first()

        if student_exist is None:
            raise BusinessValidationError(
                status_code=400, error_code='ENROLLMENT002', error_message='Student does not exist')
        else:
            enrollment_details = db.session.query(
                Enrollment).filter_by(student_id=student_id).all()

            if enrollment_details:
                return enrollment_details
            else:
                raise NotFoundError(status_code=404)

    def delete(self, student_id, course_id):
        student_exist = db.session.query(
            Student).filter(Student.student_id == student_id).first()

        if student_exist is None:
            raise BusinessValidationError(
                status_code=400, error_code='ENROLLMENT002', error_message='Student does not exist')

        course_exist = db.session.query(
            Course).filter(Course.course_id == course_id).first()

        if course_exist is None:
            raise BusinessValidationError(
                status_code=400, error_code='ENROLLMENT001', error_message='Course does not exist')

        if student_exist and course_exist:
            course = db.session.query(
                Enrollment).filter_by(student_id=student_id, course_id=course_id).first()

            if course:
                db.session.delete(course)
                db.session.commit()
            else:
                raise NotFoundError(status_code=404)

            return '', 200

    @marshal_with(enrollment_response_fields)
    def post(self, student_id):
        course_id = int(reqparse.RequestParser().add_argument(
            'course_id').parse_args().get('course_id', None))

        if course_id is None:
            raise BusinessValidationError(
                status_code=400, error_code='ENROLLMENT003', error_message='Course code required and should be string')

        student = db.session.query(Student).filter(
            Student.student_id == student_id).first()

        if student is None:
            raise NotFoundError(status_code=404)

        enroll_student = Enrollment(
            student_id=student_id, course_id=course_id)
        db.session.add(enroll_student)
        db.session.commit()

        enrollment_details = db.session.query(
            Enrollment).filter_by(student_id=student_id, course_id=course_id).first()

        return enrollment_details, 201

####### Define API Routes #######


api.add_resource(CourseAPI, '/api/course', '/api/course/<int:course_id>')
api.add_resource(StudentAPI, '/api/student', '/api/student/<int:student_id>')
api.add_resource(EnrollmentAPI, '/api/student/<int:student_id>/course',
                 '/api/student/<int:student_id>/course/<int:course_id>')

if __name__ == '__main__':
    app.run()
