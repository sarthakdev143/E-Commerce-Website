let tl1 = gsap.timeline();

tl1.from("h2", {
    opacity: 0,
    duration: 0.5,
    y: -20,
})

tl1.from(".search", {
    opacity: 0,
    duration: 0.5,
    y: -20,
})

let tl2 = gsap.timeline()

tl2.from(".product", {
    opacity: 0,
    duration: 0.3,
    y: -20,
    stagger: 0.15,
})