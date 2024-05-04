gsap.from(".nav-part", {
    opacity: 0,
    duration: 0.5,
    y: -20,
    stagger: 0.2
})

let tl1 = gsap.timeline()

tl1.from(".product", {
    opacity: 0,
    duration: 0.3,
    y: -20,
    stagger: 0.15,
})

gsap.from(".image", {
    opacity: 0,
    duration: 0.3,
    delay: 0.3,
    y: -20,
})

let tl2 = gsap.timeline()

tl2.from(".content-child", {
    opacity: 0,
    duration: 0.6,
    delay: 0.3,
    y: -20,
    stagger: 0.2
})