import { useEffect, useState } from 'react'

interface Message { page: string }

function App() {
  const [message, setMessage] = useState<Message | null>(null)

  useEffect(() => {
    fetch('/api')
      .then(res => res.json())
      .then(setMessage)
      .catch(() => setMessage({ page: 'failed to load' }))
  }, [])

  return (
    <div>
      <h1>Prau Riu Hotel</h1>
      <pre>{JSON.stringify(message)}</pre>
    </div>
  )
}

export default App
