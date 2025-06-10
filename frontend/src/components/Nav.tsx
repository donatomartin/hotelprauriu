import React from 'react'

const Nav = () => (
  <nav className="navbar navbar-expand-lg navbar-light bg-light position-fixed top-0 start-0 w-100 bg-white shadow-sm">
    <div className="container">
      <a className="navbar-brand" href="/#">
        <img src="/images/hotel/logo.webp" alt="Hotel Prauriu Logo" height="30" className="d-inline-block align-text-top me-2" />
        Prau Ríu Hotel
      </a>
      <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <div className="ms-auto">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link active" href="/#">Inicio</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/reservation">Reserva</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/#about">Sobre nosotros</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/#services">Servicios</a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/colabs">Colabs</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
)

export default Nav
