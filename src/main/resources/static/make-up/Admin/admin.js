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

// Display Product Image preview
function previewThumbnail(input) {
    var preview = document.getElementById('product-img-preview');
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
            preview.style.display = 'block'; // Show the preview
        }
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.style.display = 'none'; // Hide the preview if no file is chosen
    }
}

// Attach event listener to Product Image input
document.getElementById('product-img-input').addEventListener('change', function () {
    previewThumbnail(this);
});