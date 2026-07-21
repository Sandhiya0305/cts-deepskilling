import React from 'react';
import { Link } from 'react-router-dom';

const TrainersList = (props) => {   // ✅ parameter named props
    if (!props.trainers || props.trainers.length === 0) {
        return <div>No trainers found</div>;
    }

    return (
        <div>
            <h3>Trainers List</h3>
            <ul>
                {props.trainers.map((trainer) => (   // ✅ props.trainers
                    <li key={trainer.TrainerId}>
                        <Link to={`/trainers/${trainer.TrainerId}`}>
                            {trainer.Name}
                        </Link>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default TrainersList;