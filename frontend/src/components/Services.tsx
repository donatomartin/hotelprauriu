import React from 'react'

const Services = () => (
  <section id="services" className="py-5">
    <div className="container">
      <h2 className="text-center mb-4">Servicios</h2>
      <p className="col-md-8 mx-auto text-center">Descubre los servicios que ofrecemos en Prau Ríu Hotel</p>
      <div className="row">
        <div className="col-md-4 text-center">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <i className="bi bi-house-door display-4 mb-3"></i>
              <h5 className="card-title mt-3">Ambiente Tranquilo y Libre de Humos</h5>
              <p className="card-text">Disfruta de un ambiente limpio y tranquilo en nuestras habitaciones, con vistas a la naturaleza y a la montaña.</p>
            </div>
          </div>
        </div>
        <div className="col-md-4 text-center">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <i className="bi bi-vinyl display-4"></i>
              <h5 className="card-title mt-4">Habitaciones Temáticas</h5>
              <p className="card-text">Disfruta de nuestras habitaciones temáticas, cada una con una decoración única y diferente en base a un artista.</p>
            </div>
          </div>
        </div>
        <div className="col-md-4 text-center">
          <div className="card border-0 shadow-sm">
            <div className="card-body">
              <i className="bi bi-brightness-high display-4 mb-3"></i>
              <h5 className="card-title mt-3">0 Emisiones</h5>
              <p className="card-text">En Prau Ríu Hotel, todas nuestras actividades y servicios son 100% sostenibles y respetuosos con el medio ambiente.</p>
            </div>
          </div>
        </div>
      </div>
      <div className="text-center mt-4">
        <a href="/reservation" className="btn btn-lg btn-primary">Haz tu reserva</a>
      </div>
    </div>
    <p className="text-center mt-5">+34 684 60 50 74 <br /> hotelprauriu@hotmail.com</p>
  </section>
)

export default Services
