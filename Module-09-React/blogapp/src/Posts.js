import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
    constructor(props) {
        super(props);
        this.state = {
            posts: [],
            error: null
        };
    }

    loadPosts() {
        fetch('https://jsonplaceholder.typicode.com/posts')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                // Map JSON data to Post objects
                const postList = data.map(item => 
                    new Post(item.userId, item.id, item.title, item.body)
                );
                this.setState({ posts: postList });
            })
            .catch(error => {
                this.setState({ error: error.message });
            });
    }

    componentDidMount() {
        console.log('Posts component mounted');
        this.loadPosts();
    }

    componentDidCatch(error, errorInfo) {
        console.error('Error caught in Posts component:', error, errorInfo);
        alert(`An error occurred: ${error.message}`);
        this.setState({ error: error.message });
    }

    render() {
        const { posts, error } = this.state;

        if (error) {
            return <div style={{ color: 'red', padding: '20px' }}>
                <h2>Error Loading Posts</h2>
                <p>{error}</p>
            </div>;
        }

        return (
            <div style={{ padding: '20px', maxWidth: '800px', margin: '0 auto' }}>
                <h1>Blog Posts</h1>
                {posts.length === 0 ? (
                    <p>Loading posts...</p>
                ) : (
                    posts.map(post => (
                        <div key={post.id} style={{ 
                            borderBottom: '1px solid #ccc', 
                            marginBottom: '20px',
                            paddingBottom: '15px'
                        }}>
                            <h3>{post.id}. {post.title}</h3>
                            <p>{post.body}</p>
                        </div>
                    ))
                )}
            </div>
        );
    }
}

export default Posts;