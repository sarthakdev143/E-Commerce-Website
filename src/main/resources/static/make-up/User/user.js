gsap.from(".nav-part", {
    opacity: 0,
    duration: 0.5,
    y: -20,
    stagger: 0.2,
    ease: "back.out(3)",
});

let tl1 = gsap.timeline();

tl1.from(".product", {
    opacity: 0,
    duration: 0.3,
    y: -20,
    stagger: 0.15,
    ease: "back.out(2)",
});

let tl2 = gsap.timeline();

tl2.from(".content-child", {
    opacity: 0,
    duration: 0.6,
    delay: 0.3,
    y: -20,
    stagger: 0.2,
});

// Login & Sign Up Page Form Loads
gsap.from("#heading", {
    opacity: 0,
    duration: 1.5,
    y: -40,
    ease: "back.out(3)",
});

let tl3 = gsap.timeline();

tl3.from(".inpAnim", {
    opacity: 0,
    duration: 0.5,
    y: -20,
    stagger: 0.2,
    ease: "back.out(5)",
});

tl3.from(".other", {
    opacity: 0,
    duration: 0.5,
});

gsap.from(".para", {
    opacity: 0,
    duration: 1,
    delay: 1.8,
})

gsap.to(".verify-form", {
    duration: 0.5,
    marginTop: 0,
    delay: 1.5,
});

// User Already Registered Text Change
let message = document.querySelector(".already-registered")

message.addEventListener("mouseenter", () => {
    message.innerHTML = "Click To Refresh The Page"
});

message.addEventListener("mouseout", () => {
    message.innerHTML = "Email is Already Registered"
});