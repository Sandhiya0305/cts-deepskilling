import React from 'react';

const courses = [
  { id: 301, name: 'Angular', date: '4/5/2021' },
  { id: 302, name: 'React', date: '6/3/2021' }
];

function CourseDetails() {
  const coursedet = (
    <div>
      {courses.map((course) => (
        <div key={course.id}>
          <h3>{course.name}</h3>
          <h4>{course.date}</h4>
        </div>
      ))}
    </div>
  );

  return (
    <div className="section mystyle1">
      <h1>Course Details</h1>
      {courses.length > 0 ? coursedet : null}
    </div>
  );
}

export default CourseDetails;
