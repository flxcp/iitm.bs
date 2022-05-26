import csv
import matplotlib.pyplot as plt
from flask import Flask
from flask import request
from flask import render_template

# Dictionary
students = {}
courses = {}

# Write to Dictionary


def dict_update(dictionary_name, primary_id, candidate_id, marks):
    if primary_id in dictionary_name.keys():
        dictionary_name[primary_id][candidate_id] = marks
    else:
        dictionary_name[primary_id] = {}
        dictionary_name[primary_id][candidate_id] = marks


# Read CSV into Dictionary
with open('data.csv', mode='r') as csv_file:
    csv_reader = csv.DictReader(csv_file)
    line_count = 0
    for row in csv_reader:
        if line_count >= 0:
            dict_update(courses, int(row[' Course id']),
                        int(row['Student id']), int(row[' Marks']))
            dict_update(students, int(row['Student id']),
                        int(row[' Course id']), int(row[' Marks']))

        line_count += 1

app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'GET':
        return render_template('main.html')
    if request.method == 'POST':
        arg_option = request.form['ID']
        arg_id = int(request.form['id_value'])

        if arg_option == 'student_id':
            students_details = students[arg_id].items()
            total_marks = sum(students[arg_id].values())

            # Compute Values & Generate HTML
            return render_template('student.html', total_marks=total_marks, student_id=arg_id, students_details=students_details)

        if arg_option == 'course_id':
            # Generate Histogram
            plt.hist(courses[arg_id].values(), bins=10)
            plt.xlabel("Marks")
            plt.ylabel("Frequency")
            plt.savefig('static/course_histogram.png')

            # Compute Values & Generate HTML
            max_marks = max(courses[arg_id].values())
            avg_marks = ((sum(courses[arg_id].values())) /
                         len(courses[arg_id].values()))

            return render_template('course.html', avg_marks=avg_marks, max_marks=max_marks)


@app.errorhandler(Exception)
def handle_exception(error):
    return render_template('error.html')


if __name__ == '__main__':
    app.run()
