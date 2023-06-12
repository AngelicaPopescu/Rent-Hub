import React from "react";
import './PropertyImagesCarousel.css'


const PropertyImagesCarousel = ({ images }) => {
  return (
    <div id="carouselExampleIndicators" className="carousel slide" data-ride="carousel" style={{ height: "fit-content" }}>
    <ol className="carousel-indicators">
      {images.map((image, index) => (
        <li data-target="#carouselExampleIndicators" data-slide-to={index} className={index === 0 ? "active" : ""} key={index}></li>
      ))}
    </ol>
    <div className="carousel-inner" style={{ height: "fit-content" }}>
      {images.map((image, index) => (
        <div className={`carousel-item ${index === 0 ? "active" : ""}`} key={index}>
          <img className="d-block w-100" src={image.path} alt={`Slide ${index}`} />
        </div>
      ))}
    </div>
    <a className="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
      <span className="carousel-control-prev-icon" aria-hidden="true"></span>
      <span className="sr-only">Previous</span>
    </a>
    <a className="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
      <span className="carousel-control-next-icon" aria-hidden="true"></span>
      <span className="sr-only">Next</span>
    </a>
  </div>
  )
};

export default PropertyImagesCarousel;
