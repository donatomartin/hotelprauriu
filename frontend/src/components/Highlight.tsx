import React from 'react'

const Highlight = () => (
  <section id="highlight" className="position-relative text-center text-white d-flex justify-content-center align-items-center">
    <div className="overlay position-absolute top-0 start-0 w-100 h-100 transparent-black-background"></div>
    <div className="container position-relative">
      <h1 className="display-3 fw-bold highlight-title">Prau Ríu Hotel</h1>
      <a href="#about" className="btn btn-primary learn-more-button bg-light text-black border-0 px-5 mx-1">Sobre nosotros</a>
      <a href="/reservation" className="btn btn-primary learn-more-button bg-light text-black border-0 px-5 mx-1">Haz tu reserva</a>
      <h2 className="highlight-title py-3">Hyundai Asturdai</h2>
      <p>+34 684 60 50 74<br/>hotelprauriu@hotmail.com</p>
    </div>
  </section>
)

export default Highlight
