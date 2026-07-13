import React from 'react';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './TrainersList';
import TrainerDetail from './TrainerDetail';
import trainersData from './TrainersMock';

function App() {
    return (
        <BrowserRouter>
            <div>
                <h1>My Academy Trainers App</h1>
                <div>
                    <Link to="/">Home</Link>
                    {' | '}
                    <Link to="/trainers">Show Trainers</Link>
                </div>
                <hr />
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route 
                        path="/trainers" 
                        element={<TrainersList trainers={trainersData} />} 
                    />
                    <Route 
                        path="/trainers/:id" 
                        element={<TrainerDetail />} 
                    />
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;