document.addEventListener("DOMContentLoaded", () => {
  const ship = document.querySelector(".ship-route img");
  let position = 0;
  const speed = 2;

  function animateShip(cb) {
    const limit = window.innerWidth - ship.width;
    if (position >= limit) {
      return cb && cb();
    }
    position += speed;
    ship.style.transform = `translateX(${position}px)`;
    requestAnimationFrame(() => animateShip(cb));
  }

  document.querySelector("form").addEventListener("submit", (e) => {
    e.preventDefault();
    animateShip(() => {
      const msg = document.createElement("p");
      msg.className = "success-message";
      msg.textContent = "Success!";
      e.target.after(msg);
      setTimeout(() => e.target.submit(), 1200);
    });
  });
});
