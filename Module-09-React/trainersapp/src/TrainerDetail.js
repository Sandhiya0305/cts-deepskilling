import React from 'react';
import { useParams } from 'react-router-dom';
import trainersData from './TrainersMock';

const TrainerDetail = () => {
    const { id } = useParams();
    const trainer = trainersData.find(t => t.TrainerId === parseInt(id));

    if (!trainer) {
        return <div>Trainer not found</div>;
    }

    return (
        <div>
            <h3>Trainers Details</h3>
            <p>
                <strong>{trainer.Name}</strong> ({trainer.Technology})
            </p>
            <p>{trainer.Email}</p>
            <p>{trainer.Phone}</p>
            <ul>
                {trainer.Skills.map((skill, index) => (
                    <li key={index}>{skill}</li>
                ))}
            </ul>
        </div>
    );
};

export default TrainerDetail;