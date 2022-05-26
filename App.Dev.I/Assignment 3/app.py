import csv
import sys
from jinja2 import Template
import matplotlib.pyplot as plt

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

# Students HTMl Template
students_template = '''
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <h1 class="mainTitle">Student Details</h1>
    <table border="1">
      <tr>
        <th>Student id</th>
        <th>Course id</th>
        <th>Marks</th>
      </tr>
      {% for sub_id, marks in students_details %}
      <tr>
          <td>{{ student_id }}</td>
          <td>{{ sub_id }}</td>
          <td>{{ marks }}</td>
      </tr>
      {% endfor %}
      <tr>
        <td colspan="2" style="text-align: center">Total Marks</td>
        <td>{{ total_marks }}</td>
      </tr>
    </table>
  </body>
</html>
'''

# Course HTML Template
courses_template = '''
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>IIT Madras</title>
  </head>
  <body>
    <h1 class="mainTitle">Course Details</h1>
    <table border="1">
      <tr>
        <td>Average Marks</td>
        <td>Maximum Marks</td>
      </tr>
      <tr>
        <td>{{ avg_marks }}</td>
        <td>{{ max_marks }}</td>
      </tr>
    </table>
    <img src="course_histogram.png" alt="course_histogram" />
  </body>
</html>
'''

# Error HTML Template
error_template = '''
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>IIT Madras</title>
  </head>
  <body>
    <h1 class="mainTitle">Wrong Inputs</h1>
    <p class="body">Something went wrong</p>
  </body>
</html>
'''

# Render Arguments.
arg_option = sys.argv[1]
arg_id = int(sys.argv[2])

try:

    if arg_option == '-c':

        # Generate Histogram
        plt.hist(courses[arg_id].values(), bins=10)
        plt.xlabel("Marks")
        plt.ylabel("Frequency")
        plt.savefig('course_histogram.png')

        # Generate HTML
        template = Template(courses_template)
        html_out = template.render(avg_marks=((sum(courses[arg_id].values(
        )))/len(courses[arg_id].values())), max_marks=max(courses[arg_id].values()))
        html_outfile = open('output.html', 'w')
        html_outfile.write(html_out)
        html_outfile.close()

    elif arg_option == '-s':

        # Generate HTML
        template = Template(students_template)
        html_out = template.render(
            students_details=students[arg_id].items(), student_id=arg_id, total_marks=sum(students[arg_id].values()))
        html_outfile = open('output.html', 'w')
        html_outfile.write(html_out)
        html_outfile.close()

except:

    # Generate HTML
    html_outfile = open('output.html', 'w')
    html_outfile.write(error_template)
    html_outfile.close()