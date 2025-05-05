document.addEventListener("DOMContentLoaded", () => {
  const el = document.querySelector(".balance-number");
  if (!el) return;
  const target = parseFloat(el.textContent);
  el.textContent = "0";
  const duration = 1000;
  let start;
  function step(timestamp) {
    if (!start) start = timestamp;
    const progress = Math.min((timestamp - start) / duration, 1);
    el.textContent = Math.floor(progress * target).toLocaleString();
    if (progress < 1) {
      requestAnimationFrame(step);
    }
  }
  requestAnimationFrame(step);
});
