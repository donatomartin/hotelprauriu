import React from 'react'

const About = () => (
  <section id="about" className="py-5 bg-light">
    <div className="container text-center">
      <h2 className="mb-4">Sobre nosotros</h2>
      <p className="lead">Prau Ríu es el primer Hotel Hyundai de España, 100% eléctrico y sostenible. Generamos nuestra propia energía fotovoltaica y con el apoyo de un Hyundai Ioniq 5 logramos la desconexión total de la red eléctrica a voluntad.</p>
      <p className="lead">En Prau Ríu Hotel Hyundai-Asturdai, disfrutarás de una estancia inmejorable a la par que reduces la huella de carbono de tu viaje. Además podrás asistir a eventos culturales 100% sostenibles, cuya energía es directamente suministrada por nuestro vehículo Hyundai.</p>
      <div className="row mt-4">
        <div className="col-md-4">
          <img src="/images/hotel/hotel1.webp" className="img-fluid rounded shadow-sm" alt="Hotel Image 1" />
        </div>
        <div className="col-md-4">
          <img src="/images/hotel/hotel2.webp" className="img-fluid rounded shadow-sm" alt="Hotel Image 2" />
        </div>
        <div className="col-md-4">
          <img src="/images/hotel/hotel3.webp" className="img-fluid rounded shadow-sm" alt="Hotel Image 3" />
        </div>
      </div>
    </div>
  </section>
)

export default About
