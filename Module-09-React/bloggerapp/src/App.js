import React from 'react';
import BookDetails from './BookDetails';
import BlogDetails from './BlogDetails';
import CourseDetails from './CourseDetails';
import './App.css';

function App() {
  return (
    <div className="app">
      <BookDetails />
      <BlogDetails />
      <CourseDetails />
    </div>
  );
}

export default App;
