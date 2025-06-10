import React from 'react'
import Nav from './components/Nav'
import Highlight from './components/Highlight'
import About from './components/About'
import Services from './components/Services'
import Footer from './components/Footer'

function App() {
  return (
    <>
      <Nav />
      <main className="mt-5">
        <Highlight />
        <About />
        <Services />
      </main>
      <Footer />
    </>
  )
}

export default App
