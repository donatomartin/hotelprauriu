window.addEventListener("DOMContentLoaded", () => {
  const name = document.querySelector('input[name="guestFullName"]');
  const email = document.querySelector('input[name="guestEmail"]');
  const phone = document.querySelector('input[name="guestPhoneNumber"]');
  const checkIn = document.querySelector('input[name="checkIn"]');
  const checkOut = document.querySelector('input[name="checkOut"]');
  const numberOfGuests = document.querySelector(
    'select[name="numberOfGuests"]',
  );
  const numberOfPets = document.querySelector('select[name="numberOfPets"]');
  const guestMessage = document.getElementById("guestMessage");
  const terms = document.querySelector('input[name="termsAccepted"]');

  const form = document.querySelector('form[action="/reservation"]');
  const overlay = document.getElementById("loadingOverlay");

  // Load saved values from localStorage if they exist
  if (name && localStorage.getItem("guestFullName")) {
    name.value = localStorage.getItem("guestFullName");
  }

  if (email && localStorage.getItem("guestEmail")) {
    email.value = localStorage.getItem("guestEmail");
  }

  if (phone && localStorage.getItem("guestPhoneNumber")) {
    phone.value = localStorage.getItem("guestPhoneNumber");
  }

  if (checkIn && localStorage.getItem("checkIn")) {
    checkIn.value = localStorage.getItem("checkIn");
  }

  if (checkOut && localStorage.getItem("checkOut")) {
    checkOut.value = localStorage.getItem("checkOut");
  }

  if (terms && localStorage.getItem("termsAccepted")) {
    terms.checked = localStorage.getItem("termsAccepted") === "true";
  }

  if (numberOfGuests && localStorage.getItem("numberOfGuests")) {
    numberOfGuests.value = localStorage.getItem("numberOfGuests");
  }

  if (numberOfPets && localStorage.getItem("numberOfPets")) {
    numberOfPets.value = localStorage.getItem("numberOfPets");
  }

  if (guestMessage && localStorage.getItem("guestMessage")) {
    guestMessage.value = localStorage.getItem("guestMessage");
  }

  // Save values to localStorage on change
  if (checkIn) {
    checkIn.addEventListener("change", (e) => {
      localStorage.setItem("checkIn", e.target.value);
    });
  }

  if (checkOut) {
    checkOut.addEventListener("change", (e) => {
      localStorage.setItem("checkOut", e.target.value);
    });
  }

  if (name) {
    name.addEventListener("change", (e) => {
      localStorage.setItem("guestFullName", e.target.value);
    });
  }

  if (email) {
    email.addEventListener("change", (e) => {
      localStorage.setItem("guestEmail", e.target.value);
    });
  }

  if (phone) {
    phone.addEventListener("change", (e) => {
      localStorage.setItem("guestPhoneNumber", e.target.value);
    });
  }

  if (form) {
    form.addEventListener("submit", () => {
      if (overlay) {
        overlay.classList.remove("d-none");
      }
    });
  }

  if (terms) {
    terms.addEventListener("change", (e) => {
      localStorage.setItem("termsAccepted", e.target.checked);
    });
  }

  if (numberOfGuests) {
    numberOfGuests.addEventListener("change", (e) => {
      localStorage.setItem("numberOfGuests", e.target.value);
    });
  }

  if (numberOfPets) {
    numberOfPets.addEventListener("change", (e) => {
      localStorage.setItem("numberOfPets", e.target.value);
    });
  }

  if (guestMessage) {
    guestMessage.addEventListener("change", (e) => {
      localStorage.setItem("guestMessage", e.target.value);
    });
  }
});
