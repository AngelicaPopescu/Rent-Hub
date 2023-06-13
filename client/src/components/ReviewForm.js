import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Rating from 'react-rating-stars-component';


const ReviewForm = ({ propertyId }) => {
  const { id } = useParams();

  const [description, setDescription] = useState('');
  const [satisfaction, setSatisfaction] = useState(0);
  const [showForm, setShowForm] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`http://localhost:8080/properties/${propertyId}/reviews`, {
        propertyId,
        description,
        satisfaction,
      });
      setErrorMessage('');
      setShowForm(false);
      console.log(response.data);
    } catch (error) {
      console.error(error.response.data);
      setErrorMessage(error.response.data.message);
    }
  };

  const handleRatingChange = (value) => {
    setSatisfaction(value);
  };

  const handleButtonClick = () => {
    setShowForm(true);
  };

  return (
    <div>
      {showForm ? (
        <form onSubmit={handleSubmit}>
          <div className='review'>
            <label>Description:</label>
            <input type='text' value={description} onChange={(e) => setDescription(e.target.value)} />
          </div>
          <div className='review'>
            <label>Satisfaction:</label>
            <Rating
              count={5}
              size={24}
              activeColor='#ffd700'
              emptyIcon={<i className='far fa-star'></i>}
              fullIcon={<i className='fas fa-star'></i>}
              onChange={handleRatingChange}
              value={satisfaction}
            />
          </div>
          <button className='review-button' type='submit'>
            Save
          </button>
          <div className='error-reservation'>{errorMessage && <p>{errorMessage}</p>}</div>
        </form>
      ) : (
        <button className='review-button' onClick={handleButtonClick}>
          Add review
        </button>
      )}
    </div>
  );
};

export default ReviewForm;
