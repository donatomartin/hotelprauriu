window.addEventListener('DOMContentLoaded', () => {
  const checkIn = document.querySelector('input[name="checkIn"]');
  const checkOut = document.querySelector('input[name="checkOut"]');
  const form = document.querySelector('form[action="/reservation"]');
  const overlay = document.getElementById('loadingOverlay');

  if (checkIn && localStorage.getItem('checkIn')) {
    checkIn.value = localStorage.getItem('checkIn');
  }
  if (checkOut && localStorage.getItem('checkOut')) {
    checkOut.value = localStorage.getItem('checkOut');
  }

  if (checkIn) {
    checkIn.addEventListener('change', e => {
      localStorage.setItem('checkIn', e.target.value);
    });
  }
  if (checkOut) {
    checkOut.addEventListener('change', e => {
      localStorage.setItem('checkOut', e.target.value);
    });
  }

  if (form) {
    form.addEventListener('submit', () => {
      if (overlay) {
        overlay.classList.remove('d-none');
      }
    });
  }
});
