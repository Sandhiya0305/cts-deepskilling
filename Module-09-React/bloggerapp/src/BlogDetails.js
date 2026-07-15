import React from 'react';

const blogs = [
  {
    id: 201,
    title: 'React Learning',
    author: 'Stephen Biz',
    content: 'Welcome to learning React!'
  },
  {
    id: 202,
    title: 'Installation',
    author: 'Schewzdenier',
    content: 'You can install React from npm.'
  }
];

function BlogDetails() {
  const content = (
    <div>
      {blogs.map((blog) => (
        <div key={blog.id}>
          <h3>{blog.title}</h3>
          <h4>{blog.author}</h4>
          <p>{blog.content}</p>
        </div>
      ))}
    </div>
  );

  return (
    <div className="section v1">
      <h1>Blog Details</h1>
      {blogs.length > 0 ? content : null}
    </div>
  );
}

export default BlogDetails;
