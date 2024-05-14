// Admin Landing Page
let tl1 = gsap.timeline();

tl1.from("h1", {
    opacity: 0,
    duration: 0.3,
})

tl1.from(".list", {
    opacity: 0,
    duration: 0.3,
    y: -20,
    ease: "power1.inOut",
    stagger: 0.125,
})

tl1.from("#form-parent", {
    opacity: 0,
    duration: 0.5,
    y: -20,
})

// View All Products
let tl2 = gsap.timeline();

tl2.from("#thead", {
    opacity: 0,
    duration: 0.3,
    y: -20,
})

tl2.from("#tr", {
    opacity: 0,
    duration: 0.5,
    y: -20,
    ease: "power1.inOut",
    stagger: 0.2
})

// Edit Product
let tl3 = gsap.timeline();

tl3.from(".back", {
    opacity: 0,
    duration: 0.3,
    y: -20,
    ease: "power1.inOut",
})